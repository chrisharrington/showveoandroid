package com.showveo.android;

import android.app.TabActivity;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.IOException;

public class TestView extends TabActivity {

	/**
	 * Called when this ActivityType is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		try {
			MediaPlayer player = new MediaPlayer();
			player.setDataSource("http://68.147.201.165:3000/");
			player.prepare();
			player.start();
		} catch (IOException e) {

		}
    }
}


