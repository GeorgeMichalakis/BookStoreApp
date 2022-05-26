public class StartFunction {
	
	/*
	 * 1. The class that instantiates everything at first.
	 * 2. Creates a Static Mainwindow object
	 * 3. Opens the main menu Frame.
	 */
	
	private static MainWindow window;
	
	public static void main(String[] args) {
		window= new MainWindow();
		try {
			window.openMainFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
