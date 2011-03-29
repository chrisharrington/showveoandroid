package com.showveo.android.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.showveo.android.R;
import domain.Movie;
import domain.UserMovie;

import java.util.List;

public class MoviesArrayAdapter extends ArrayAdapter<UserMovie> {

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Data Members

	//	The collection of objects associated with the adapter.
	private final List<UserMovie> _objects;

	//	Inflates layout.
	private final LayoutInflater _inflater;

	//	The resource ID.
	private final int _resource;

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Constructors

	/**
	 * The default constructor.
	 * @param context The context in which the array adapter operates.
	 * @param resource The array adapter's resource.
	 * @param objects The list of objects to bind to the adapter.
	 * @param inflater Inflates layout.
	 */
	public MoviesArrayAdapter(Context context, int resource, List<UserMovie> objects, LayoutInflater inflater) {
		super(context, resource, objects);

		if (objects == null)
			throw new IllegalArgumentException("objects");

		_objects = objects;
		_resource = resource;
		_inflater = inflater;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * The overridden getView method.
	 * @param position The position.
	 * @param view The view.
	 * @param parent The parent.
	 * @return The view.
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null)
			view = _inflater.inflate(_resource, null);

		final UserMovie info = _objects.get(position);
		if (info == null)
			return view;

		final Movie movie = info.getMovie();

		TextView name = (TextView) view.findViewById(R.id.tvName);
		if (name != null)
			name.setText(movie.getName() + " (" + movie.getYear() + ")");

		TextView encoding = (TextView) view.findViewById(R.id.tvIsEncoding);
		if (encoding != null)
			encoding.setVisibility(movie.isEncoded() ? View.GONE : View.VISIBLE);

		TextView actors = (TextView) view.findViewById(R.id.tvActors);
		if (actors != null)
			actors.setText(joinString(movie.getActors()));

		TextView description = (TextView) view.findViewById(R.id.tvDescription);
		if (description != null)
			description.setText(movie.getSynopsis());

		return view;
	}

	//----------------------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Derives a comma-separated string from a list of strings.
	 * @param strings The strings to join together.
	 * @return THe concatenated string.
	 */
	private String joinString(List<String> strings) {
		String joined = "";
		for (String string : strings)
			joined += ", " + string;
		return joined.substring(2);
	}
}
