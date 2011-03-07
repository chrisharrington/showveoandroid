package com.showveo.android;

import android.app.Activity;
import android.app.ProgressDialog;
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
	//	Data Members

	//	The progress dialog for the loading message.
	private ProgressDialog _loading;

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

	/**
	 * Shows a losding message.
	 * * @param message The loading message to show.
	 */
	public void showLoading(String message) {
		if (message == null || message.equals(""))
			message = "Loading. Please wait...";

		if (_loading != null)
			_loading.hide();

		_loading = ProgressDialog.show(this, "", message, true);
	}

	/**
	 * Hides any loading message.
	 */
	public void hideLoading() {
		if (_loading == null)
			return;

		_loading.dismiss();
		_loading = null;
	}
}

