package userInterface;

public class ApplicationLaunch {

	public static void main(String[] args) {
		int choice1 = UserMenu.applicationLaunch();
		UserMenu.loginChoicePage(choice1);
	}
}
