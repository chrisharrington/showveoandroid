package com.showveo.android;

import com.showveo.android.R;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.TextView;
import base.Loader;
import container.IDataStore;
import view.ActivityType;

/**
 * The activity that gets loaded first.  Responsible for application initialization.
 */
public class SplashView extends BaseView {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

    /**
     * Called during the onCreate method of the activity, before the onLoad handler is fired.
     * @param arg Any arguments passed from the calling activity.
     */
    @Override
    protected void run(Object arg) {
        setContentView(R.layout.splash);

        new LoadApplicationTask().execute((IDataStore) getApplication());
    }

	/**
	 * An asynchronous task used to load the application in the background.
	 */
	private class LoadApplicationTask extends AsyncTask<IDataStore, Void, Throwable> {

		@Override
		protected Throwable doInBackground(IDataStore... stores) {
			try {
				Loader.load(stores[0]);
				return null;
			} catch (Throwable t) {
				return t;
			}
		}

		@Override
		protected void onPostExecute(Throwable result) {
			if (result != null) {
				TextView text = (TextView) findViewById(R.id.textFeedback);
				if (text != null) {
					text.setText("An error has occurred during application initialization.  Sorry!");
					text.setTextColor(Color.RED);
				}
				return;
			}

			loadActivity(ActivityType.Main);
		}
	}
}