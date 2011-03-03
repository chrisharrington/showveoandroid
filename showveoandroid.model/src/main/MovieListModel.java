package main;

import dataaccess.usermovie.IUserMovieRepository;
import model.IMovieListModel;
import view.IMovieListView;

/**
 * The model for the movie list page.
 */
public class MovieListModel implements IMovieListModel {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The movie list view.
	private final IMovieListView _view;

	//	A container for user-movie information.
	private final IUserMovieRepository _userMovieRepository;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	public MovieListModel(IMovieListView view, IUserMovieRepository userMovieRepository) {
		if (view == null)
			throw new IllegalArgumentException("view");
		if (userMovieRepository == null)
			throw new IllegalArgumentException("userMovieRepository");

		_view = view;
		_userMovieRepository = userMovieRepository;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads the recent movies for the logged in user.
	 */
	public void loadRecentMovies() {
		_view.setRecentMovies(_userMovieRepository.getRecentByUser());
	}

}
