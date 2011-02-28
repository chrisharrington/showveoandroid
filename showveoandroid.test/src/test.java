import container.DR;
import dataaccess.user.IUserRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.User;
import domain.UserMovie;
import org.junit.Test;
import service.security.ICryptographer;
import service.serialization.ISerializer;
import service.session.ISessionManager;

import java.util.List;

public class test {

	@Test
	public void blah() {
		Loader.load();

		ISerializer serializer = DR.get(ISerializer.class);

		ICryptographer cryptographer = DR.get(ICryptographer.class);
		IUserRepository userRepository = DR.get(IUserRepository.class);
		User user = userRepository.getByEmailAddressAndPassword("chrisharrington99@gmail.com", cryptographer.sha256hash("test"));

		ISessionManager sessionManager = DR.get(ISessionManager.class);
		sessionManager.register(user);

		IUserMovieRepository userMovieRepository = DR.get(IUserMovieRepository.class);
		List<UserMovie> userMovies = userMovieRepository.getRecentByUser(user);
	}

}
