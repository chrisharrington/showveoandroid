package com.showveo.android;

import android.app.Activity;
import android.content.Intent;
import com.showveo.android.main.MainView;
import com.showveo.android.movies.MoviesView;
import view.ActivityType;
import view.IBaseView;

/**
 * Provides basic view functionality.
 */
public abstract class BaseView extends Activity implements IBaseView {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Switches to another activity.
	 * @param type The type of activity.
	 */
	public void switchActivity(ActivityType type) {
		Intent intent;
		switch (type) {
			case Main:
				intent = new Intent(this, MainView.class);
				break;
			case Movies:
				intent = new Intent(this, MoviesView.class);
				break;
			default:
				throw new IllegalArgumentException("activityType");
		}

		startActivity(intent);
	}

}

