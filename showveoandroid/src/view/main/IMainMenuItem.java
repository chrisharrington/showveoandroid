package view.main;

public interface IMainMenuItem {

	void setTitle(String value);
	String getTitle();

	void setDescription(String value);
	String getDescription();

	void setType(MainMenuType type);
	MainMenuType getType();
}
