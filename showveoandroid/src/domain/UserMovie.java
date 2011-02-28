package domain;

/**
 * Defines user-movie information.
 */
public class UserMovie {

	private User _user;
	private Movie _movie;
	private boolean _isFavorite;

	public void setUser(User value) { _user = value; }
	public User getUser() { return _user; }

	public void setMovie(Movie value) { _movie = value; }
	public Movie getMovie() { return _movie; }

	public void isFavorite(boolean value) { _isFavorite = value; }
	public boolean isFavorite() { return _isFavorite; }

}
