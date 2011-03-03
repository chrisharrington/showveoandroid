package main;

import view.IMainMenuItem;

public class MainMenuItem implements IMainMenuItem {

	private String _title;
	private String _description;

	public MainMenuItem(String title, String description) {
		_title = title;
		_description = description;
	}

	public void setTitle(String value) { _title = value; }
	public String getTitle() { return _title; }

	public void setDescription(String value) { _description = value; }
	public String getDescription() { return _description; }

}
