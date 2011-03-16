import base.Loader;
import container.DR;
import container.IDataStore;
import container.ILoader;
import dataaccess.user.IUserRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.User;
import domain.UserMovie;
import org.junit.Test;
import service.security.ICryptographer;
import service.session.ISessionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class test {

	@Test
	public void blah() {
		ILoader loader = new Loader();
		loader.load(new DataStore());

		ICryptographer cryptographer = DR.get(ICryptographer.class);
		String password = cryptographer.sha256hash("test");

		IUserRepository userRepository = DR.get(IUserRepository.class);
		User user = userRepository.getByEmailAddressAndPassword("chrisharrington99@gmail.com", password);

		ISessionManager sessionManager = DR.get(ISessionManager.class);
		sessionManager.register(user);

		IUserMovieRepository userMovieRepository = DR.get(IUserMovieRepository.class);
		List<UserMovie> infos = userMovieRepository.getAll();
	}

	private class DataStore implements IDataStore {

		private final Map<UUID, Object> _map;

		public DataStore() {
			_map = new HashMap<UUID, Object>();
		}

		public <T> UUID addData(T data) {
			UUID id = UUID.randomUUID();
			_map.put(id, data);
			return id;
		}

		public <T> T getData(UUID id, Class<T> type) {
			T data = type.cast(_map.get(id));
			_map.remove(id);
			return data;
		}
	}

}
