package domain;

import domain.UserMovie;

import java.util.ArrayList;
import java.util.Collection;

public class UserMovieCollection extends ArrayList<UserMovie> {
	public UserMovieCollection(Collection<UserMovie> collection) {
		super(collection);
	}

}
