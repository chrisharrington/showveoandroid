package com.showveo.android.movies;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.showveo.android.R;
import com.showveo.android.ShowveoApplication;
import domain.UserMovieCollection;

import java.util.UUID;

/**
 * An activity representing a list of movies.
 */
public class MoviesTab extends Activity {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Overridden Methods

	/**
	 * Called when this ActivityType is first created.
	 * @param savedInstanceState Any saved instance state information.
	 */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		if (extras == null)
			return;

		UUID id = UUID.fromString(extras.getString("tabmoviesid"));
		UserMovieCollection infos = ((ShowveoApplication) getApplication()).getData(id, UserMovieCollection.class);

		ListView list = new ListView(this);
		list.setAdapter(new MoviesArrayAdapter(this, R.layout.movielistview, infos, (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)));
		list.setOnItemClickListener(new ListView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

			}
		});
		setContentView(list);
    }

}
