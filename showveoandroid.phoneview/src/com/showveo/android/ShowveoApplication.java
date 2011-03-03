package com.showveo.android;

import android.app.Application;
import base.Loader;
import container.ILoader;

/**
 * The global application class for the Showveo application.
 */
public class ShowveoApplication extends Application {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 */
	public ShowveoApplication() {
		ILoader loader = new Loader();
		loader.load();
	}

}
