package ui.main;

//UI designÇÏ´Â component
import javax.swing.UIManager;

public class SCSMainServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SCSMainFrame frame = new SCSMainFrame();
		frame.setVisible(true);
		frame.runServer();
	}

}
