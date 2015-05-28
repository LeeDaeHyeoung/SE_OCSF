package ui.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import object.shared.SCRoom;

public class SCCRoomList extends JList implements MouseListener {

	private static final long serialVersionUID = 1L;

	private DefaultListModel model;
	private SCCMainPanel mainPanel;

	public SCCRoomList() {
		super();
		model = new DefaultListModel();
		this.setModel(model);
		this.addMouseListener(this);
	}

	public void setRoomList(Vector<SCRoom> roomList) {
		model.removeAllElements();
		for (SCRoom room : roomList) {
			model.addElement(room);
		}
	}

	public void setMainPanel(SCCMainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	public void mouseReleased(MouseEvent e) {
		mainPanel.applyEnterRoom((SCRoom) this.getSelectedValue());
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {

	}
}
