package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Defines movie information.
 */
public class Movie {

	private String _id;
	private String _name;
	private int _year;
	private String _synopsis;
	private List<Genre> _genres;
	private Date _addDate;
	private User _lastWatched;
	private Date _lastWatchedDate;
	private String _poster;
	private String _director;
	private List<String> _actors;
	private String _file;
	private boolean _isEncoded;
	private String _url;

	public void setID(String value) { _id = value; }
	public String getID() { return _id; }

	public void setName(String value) { _name = value; }
	public String getName() { return _name; }

	public void setYear(int value) { _year = value; }
	public int getYear() { return _year; }

	public void setSynopsis(String value) { _synopsis = value; }
	public String getSynopsis() { return _synopsis; }

	public void setGenres(List<Genre> value) { _genres = value; }
	public List<Genre> getGenres() { return _genres; }

	public void setAddDate(Date value) { _addDate = value; }
	public Date getAddDate() { return _addDate; }

	public void setLastWatched(User value) { _lastWatched = value; }
	public User getLastWatched() { return _lastWatched; }

	public void setLastWatchedDate(Date value) { _lastWatchedDate = value; }
	public Date getLastWatchedDate() { return _lastWatchedDate; }

	public void setPoster(String value) { _poster = value; }
	public String getPoster() { return _poster; }

	public void setDirector(String value) { _director = value; }
	public String getDirector() { return _director; }

	public void setActors(List<String> value) { _actors = value; }
	public List<String> getActors() { return _actors; }

	public void setFile(String value) { _file = value; }
	public String getFile() { return _file; }

	public void isEncoded(boolean value) { _isEncoded = value; }
	public boolean isEncoded() { return _isEncoded; }

	public void setUrl(String value) { _url = value; }
	public String getUrl() { return _url; }

	public Movie() {
		_genres = new ArrayList<Genre>();
		_actors = new ArrayList<String>();
	}
}
