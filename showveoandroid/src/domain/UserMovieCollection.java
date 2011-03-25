package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserMovieCollection extends ArrayList<UserMovie> implements List<UserMovie> {
	public UserMovieCollection() {}

	public UserMovieCollection(Collection<UserMovie> collection) {
		super(collection);
	}

}
