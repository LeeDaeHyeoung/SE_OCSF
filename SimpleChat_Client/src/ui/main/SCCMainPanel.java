package ui.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class SCCMainPanel extends JPanel implements ActionListener {

	private SCCRoomList roomList;
	
	private static final long serialVersionUID = 1L;

	
	public void setRoomList(SCCRoomList roomList) {
		this.roomList = roomList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
