package ui.main;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class SCCMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private SCCMainPanel mainPanel;
	private SCCRoomList roomList;
	private JScrollPane scrollPane;
	
	public SCCMainFrame() {
		super("SimpleChat - Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		mainPanel = new SCCMainPanel();
		this.add(BorderLayout.CENTER, mainPanel);
		
		roomList = new SCCRoomList();
		scrollPane = new JScrollPane(roomList);
		scrollPane.setPreferredSize(new Dimension(140,450));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(BorderLayout.EAST, scrollPane);
		
		mainPanel.setRoomList(roomList);
		roomList.setMainPanel(mainPanel);
		
		this.pack();
	}
}
