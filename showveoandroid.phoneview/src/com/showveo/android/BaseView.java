package com.showveo.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.showveo.android.main.MainView;
import com.showveo.android.movies.MoviesView;
import service.event.IEmptyEventHandler;
import view.ActivityType;
import view.IBaseView;

/**
 * Provides basic view functionality.
 */
public abstract class BaseView extends Activity implements IBaseView {

	//------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The progress dialog for the loading message.
	private ProgressDialog _loading;

	//	The load event handler.  Fired after the view loads.
	private IEmptyEventHandler _onLoad;

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Called when this ActivityType is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);

		if(_onLoad != null)
			_onLoad.run();
    }

	/**
	 * Switches to another activity.
	 * @param type The type of activity.
	 */
	public void loadActivity(ActivityType type) {
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
	 * Loads the OS-defined movie activity.
	 * @param url The url of the movie to show.
	 */
	public void loadMovieActivity(String url) {
		if (url == null || url.equals(""))
			throw new IllegalArgumentException("url");

		Intent movieIntent = new Intent(Intent.ACTION_VIEW);
		movieIntent.setDataAndType(Uri.parse(url), "video/mp4");
		startActivity(movieIntent);
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

	/**
	 * Shows a standard message to the user.
	 * @param message The message to show.
	 */
	public void showMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}

	/**
	 * Shows an error message to the user.
	 * @param message The error message.
	 */
	public void showErrorMessage(String message) {
		showMessage(message);
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Event Handlers

	/**
	 * Fired after the view has loaded.
	 * @param handler The event handler.
	 */
	public void onLoadHandler(IEmptyEventHandler handler) {
		if (handler == null)
			throw new IllegalArgumentException("handler");

		_onLoad = handler;
	}
}

