package com.showveo.android;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

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

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost();  // The activity TabHost
		//TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		TabHost.TabSpec spec;  // Resusable TabSpec for each tab
		Intent intent;  // Reusable Intent for each tab

		intent = new Intent().setClass(this, FirstTab.class);
		spec = tabHost.newTabSpec("recent").setIndicator("Recent").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SecondTab.class);
		spec = tabHost.newTabSpec("favorites").setIndicator("Favorites").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, FirstTab.class);
		spec = tabHost.newTabSpec("genres").setIndicator("Genres").setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SecondTab.class);
		spec = tabHost.newTabSpec("all").setIndicator("All").setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(1);
    }
}


