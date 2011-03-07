import base.Loader;
import container.DR;
import container.ILoader;
import dataaccess.user.IUserRepository;
import dataaccess.usermovie.IUserMovieRepository;
import domain.User;
import domain.UserMovie;
import org.junit.Test;
import service.security.ICryptographer;
import service.session.ISessionManager;

import java.util.List;

public class test {

	@Test
	public void blah() {
		ILoader loader = new Loader();
		//loader.load(new ShowveoApplication());

		ICryptographer cryptographer = DR.get(ICryptographer.class);
		String password = cryptographer.sha256hash("test");

		IUserRepository userRepository = DR.get(IUserRepository.class);
		User user = userRepository.getByEmailAddressAndPassword("chrisharrington99@gmail.com", password);

		ISessionManager sessionManager = DR.get(ISessionManager.class);
		sessionManager.register(user);

		IUserMovieRepository userMovieRepository = DR.get(IUserMovieRepository.class);
		List<UserMovie> infos = userMovieRepository.getAll();
	}

}
