package ui.main;

import javax.swing.JFrame;

import net.main.ServerManagerPanel;;

public class SCSMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ServerManagerPanel mainPanel;
	
	public SCSMainFrame() {
		super("SimpleChat - Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new ServerManagerPanel();
		this.setContentPane(mainPanel);
		this.pack();
	}

	public void runServer() {
		// TODO Auto-generated method stub
		mainPanel.start();
	}
}