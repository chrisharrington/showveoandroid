package domain;

import java.util.ArrayList;
import java.util.Collection;

public class GenreCollection extends ArrayList<Genre> {
	public GenreCollection() {}

	public GenreCollection(Collection<Genre> collection) {
		super(collection);
	}
}
