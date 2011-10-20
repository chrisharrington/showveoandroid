import base.Loader;
import base.TestDataStore;
import container.DR;
import container.IDataStore;
import dataaccess.genre.IGenreRepository;
import org.junit.Test;

import java.util.UUID;

public class test {

	@Test
	public void blah() {
        Loader.load(new TestDataStore());

        IGenreRepository repo = DR.get(IGenreRepository.class);
	}

}
