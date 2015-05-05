package ui.main;

import javax.swing.UIManager;

public class SCCMainClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		SCCMainFrame frame = new SCCMainFrame();
		frame.setVisible(true);
	}

}
