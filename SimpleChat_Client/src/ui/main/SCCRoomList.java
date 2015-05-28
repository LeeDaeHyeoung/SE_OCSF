package ui.main;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import object.shared.SCRoom;


public class SCCRoomList extends JList implements MouseListener{

	
	private static final long serialVersionUID = 1L;
	
	private DefaultListModel model;
	private SCCMainPanel mainPanel;

	public void setMainPanel(SCCMainPanel mainPanel) {
		// TODO Auto-generated method stub
		this.mainPanel = mainPanel;
	}
	public void setRoomList(Vector<SCRoom> roomList) {
		model.removeAllElements();
		for(SCRoom room: roomList){
			model.addElement(room);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
