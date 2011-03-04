package movies;

import base.BaseModel;
import dataaccess.usermovie.IUserMovieRepository;
import model.IMoviesModel;
import view.movies.IMoviesView;

public class MoviesModel extends BaseModel<IMoviesView> implements IMoviesModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	public MoviesModel(IUserMovieRepository userMovieRepository) {
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");

		_userMovieRepository = userMovieRepository;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads all of the movies.
	 */
	public void loadMovies() {
		_view.setMovies(_userMovieRepository.getAll());
	}
}
