package model.movies;

import container.IContainer;
import service.event.IParameterizedEventHandler;

/**
 * Defines an interface used to load movies for the movies model.
 */
public interface ILoadMoviesRunner {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Loads the movies.
	 * @param callback The callback function to execute once everything has been retrieved.
	 */
	void run(IParameterizedEventHandler<IContainer> callback);

}
