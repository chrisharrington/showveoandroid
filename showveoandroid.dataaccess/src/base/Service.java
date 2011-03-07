package base;

import dataaccess.IQuery;
import dataaccess.IService;
import domain.User;
import service.serialization.ISerializer;
import service.session.ISessionManager;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Service implements IService {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members
	
	//	The location of the remote service.
	private String _remote;

	//	A flag indicating whether or not fixtures should be used.
	private boolean _useFixtures;

	//	The path to the fixtures folder.
	private String _fixturesPath;

	//	Deserializes response data.
	private ISerializer _serializer;

	//	Retrieves the currently logged in user.
	private ISessionManager _sessionManager;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors
	
	/*
	 * The default constructor.
	 * @param remote The location of the remote service.
	 * @param serializer Deserializes response data.
	 * @param sessionManager Retrieves the currently logged in user.
	 * @param useFixtures A flag indicating whether or not fixtures should be used.
	 */
	public Service(String remote, ISerializer serializer, ISessionManager sessionManager, boolean useFixtures) {
		if (remote == null || remote.equals(""))
			throw new IllegalArgumentException("remote");
		if (serializer == null)
			throw new IllegalArgumentException("serializer");
		if (sessionManager == null)
			throw new IllegalArgumentException("sessionManager");
		
		_remote = remote;
		_serializer = serializer;
		_sessionManager = sessionManager;
		_useFixtures = useFixtures;
		_fixturesPath = "/home/chris/Code/showveoandroid/showveoandroid.dataaccess/src/fixtures/";
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods
	
	/*
	 * Makes a remote call to the location specified by the given query.
	 * @param query The query to execute.  Contains locations and data required for execution.
	 * @return The result of the executed query.
	 */
	public <T> T execute(IQuery<T> query) {
		if (query == null)
			throw new IllegalArgumentException("query");

		if (_useFixtures)
			return query.getType().cast(fromFixture(query));

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(_remote + query.getQueryString()).openConnection();

			User user = _sessionManager.get();
			if (user != null)
				connection.setRequestProperty("Cookie", "identity=" + user.getIdentity());

			connection.setRequestMethod(query.getMethod());
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
			}

			System.out.println(builder);

			reader.close();

			return query.getType().cast(_serializer.deserialize(builder.toString(), query.getType()));
		}
		catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Makes a remote call to the location specified by the given query.
	 *
	 * @param query The query to execute.  Contains locations and data required for execution.
	 * @return The list result of the executed query.
	 */
	public <T> List<T> executeList(IQuery<T> query) {
		if (query == null)
			throw new IllegalArgumentException("query");

		if (_useFixtures)
			return listFromFixture(query);

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(_remote + query.getQueryString()).openConnection();

			User user = _sessionManager.get();
			if (user != null)
				connection.setRequestProperty("Cookie", "identity=" + user.getIdentity());

			connection.setRequestMethod(query.getMethod());
			connection.setDoOutput(true);
			connection.setReadTimeout(10000);
			connection.connect();

			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
				builder.append(line).append("\n");

			reader.close();

			return _serializer.deserializeList(builder.toString(), query.getType());
		}
		catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Called when the query should return results from a local fixture instead of a remote location.  Used for testing.
	 * @param query The query to handle.
	 * @return The result of the query.
	 */
	private <T> T fromFixture(IQuery<T> query) {
		String name = query.getQueryString();
		name = name.substring(name.lastIndexOf("/")+1);
		name = name.substring(0, name.indexOf("?"));

		String path = _fixturesPath + name;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));

			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
				builder.append(line);

			reader.close();

			return _serializer.deserialize(builder.toString(), query.getType());
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Called when the query should return results from a local fixture instead of a remote location.  Used for testing.
	 * @param query The query to handle.
	 * @return The result of the query.
	 */
	private <T> List<T> listFromFixture(IQuery<T> query) {
		String name = query.getQueryString();
		name = name.substring(name.lastIndexOf("/")+1);
		if (name.indexOf("?") > -1)
			name = name.substring(0, name.indexOf("?"));

		String path = _fixturesPath + name;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));

			StringBuilder builder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null)
				builder.append(line);

			reader.close();

			return _serializer.deserializeList(builder.toString(), query.getType());
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
}
