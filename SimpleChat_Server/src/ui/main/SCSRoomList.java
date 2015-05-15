package ui.main;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.main.ServerManagerPanel;
import object.shared.SCRoom;

public class SCSRoomList extends JList{

	private static final long serialVersionUID = 1L;
	
	private DefaultListModel model;
	private ServerManagerPanel mainPanel;
	private JTextArea messageArea;
	private JScrollPane scrollPane;
	
	public SCSRoomList(){
		super();
		model = new DefaultListModel();
		this.setModel(model);
		
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		scrollPane = new JScrollPane(messageArea);
		scrollPane.setPreferredSize(new Dimension(385,600));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
	}
	
	public void setRoomList(Vector<SCRoom> roomList){
		model.removeAllElements();
		for(SCRoom room : roomList){
			model.addElement(room);
		}
	}
	
	public void setMainPanel(ServerManagerPanel mainPanel){
		this.mainPanel = mainPanel;
	}
}
