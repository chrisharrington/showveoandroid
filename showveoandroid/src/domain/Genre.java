package domain;

/**
 * A DTO for genre information.
 */
public class Genre {

	private String _id;
	private String _name;

	public String getID() { return _id; }
	public void setID(String value) { _id = value; }

	public String getName() { return _name; }
	public void setName(String value) { _name = value; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Genre genre = (Genre) o;

		return !(_id != null ? !_id.equals(genre._id) : genre._id != null) && !(_name != null ? !_name.equals(genre._name) : genre._name != null);

	}

	@Override
	public int hashCode() {
		int result = _id != null ? _id.hashCode() : 0;
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		return result;
	}
}
