package GameMechanics;

public class Launcher {

	public Launcher() {
        MainMenu.startingWindow();
        BJTable.gameWindow();
        GameLoop.runBJ();
	}

	public static void main(String[] args) {
		new Launcher();
	}
}