package com.showveo.android;

import android.app.Application;
import base.Loader;
import container.IDataStore;
import container.ILoader;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * The global application class for the Showveo application.
 */
public class ShowveoApplication extends Application implements IDataStore {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The map containing activity data.
	private final Map<UUID, Object> _data;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 */
	public ShowveoApplication() {
		_data = new Hashtable<UUID, Object>();

		ILoader loader = new Loader();
		loader.load(this);
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Adds data to the activity data store.
	 * @param data The data to add.
	 * @param <T> The type of data to add.
	 * @return The key for retrieving the data.
	 */
	public <T> UUID addData(T data) {
		UUID id = UUID.randomUUID();
		_data.put(id, data);
		return id;
	}

	/**
	 * Retreives the data stored with the given ID.  The data is removed from the store after retrieval.
	 * @param id The data key.
	 * @param type The type of data to retrieve.
	 * @param <T> The type of data to retrieve.
	 * @return The retrieved data.
	 */
	public <T> T getData(UUID id, Class<T> type) {
		T data = type.cast(_data.get(id));
		_data.remove(id);
		return data;
	}

}
