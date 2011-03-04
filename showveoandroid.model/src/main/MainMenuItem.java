package main;

import view.main.IMainMenuItem;
import view.main.MainMenuType;

public class MainMenuItem implements IMainMenuItem {

	private String _title;
	private String _description;
	private MainMenuType _type;

	public MainMenuItem(String title, String description, MainMenuType type) {
		_title = title;
		_description = description;
		_type = type;
	}

	public void setTitle(String value) { _title = value; }
	public String getTitle() { return _title; }

	public void setDescription(String value) { _description = value; }
	public String getDescription() { return _description; }

	public void setType(MainMenuType type) { _type = type; }
	public MainMenuType getType() { return _type; }

}
