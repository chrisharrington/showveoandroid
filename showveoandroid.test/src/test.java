import dataaccess.user.IUserRepository;
import domain.User;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class test {

	@Test
   public void blah() {
		Loader.load();

		IUserRepository userRepository = DR.get(IUserRepository.class);
		User user = userRepository.getByEmailAddressAndPassword("email", "password");
   }

}
