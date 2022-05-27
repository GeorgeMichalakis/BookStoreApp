public class StartFunction {
	private static MainWindow window;
	
	public static void main(String[] args) {
		window= new MainWindow();
		try {
			window.openMainFrame();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
