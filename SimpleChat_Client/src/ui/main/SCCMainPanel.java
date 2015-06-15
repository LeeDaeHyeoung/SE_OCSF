package ui.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.main.SCCConnectionManager;
import object.shared.SCPacket;
import object.shared.SCRoom;

public class SCCMainPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JLabel addressLabel; //
	private JTextField addressField; //
	private JLabel portLabel; //
	private JTextField portField; //
	private JButton connectBtn;
	private JLabel announcementLabel;
	private JTextArea announcementArea;
	private JScrollPane scrollPane_a;
	private JLabel roomLabel;
	private JTextField roomField;
	private JButton roomBtn;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JLabel msgLabel;
	private JTextField msgField;
	private JButton msgBtn;
	private SCCConnectionManager connectionManager;

	private SCCRoomList roomList;

	private SCRoom currentRoom;

	public SCCMainPanel() {
		super(new FlowLayout(FlowLayout.LEFT, 8, 4));
		this.setPreferredSize(new Dimension(400, 450));

		addressLabel = new JLabel("Sever Address");
		addressLabel.setPreferredSize(new Dimension(110, 20));
		this.add(addressLabel);

		addressField = new JTextField("155.230.14.91");
		addressField.setPreferredSize(new Dimension(180, 20));
		this.add(addressField);

		portLabel = new JLabel("Server Port");
		portLabel.setPreferredSize(new Dimension(110, 20));
		this.add(portLabel);

		portField = new JTextField("8000");
		portField.setPreferredSize(new Dimension(180, 20));
		this.add(portField);

		connectBtn = new JButton("Connect");
		connectBtn.setPreferredSize(new Dimension(80, 20));
		connectBtn.addActionListener(this);
		connectBtn.setActionCommand("Connect");
		this.add(connectBtn);

		announcementLabel = new JLabel("Announcement");
		announcementLabel.setPreferredSize(new Dimension(400, 20));
		this.add(announcementLabel);

		announcementArea = new JTextArea();
		announcementArea.setEditable(false);
		scrollPane_a = new JScrollPane(announcementArea);
		scrollPane_a.setPreferredSize(new Dimension(385, 100));
		scrollPane_a
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane_a);

		roomLabel = new JLabel("ROOM TITLE");
		roomLabel.setPreferredSize(new Dimension(110, 20));
		this.add(roomLabel);

		roomField = new JTextField();
		roomField.setEnabled(false);
		roomField.setPreferredSize(new Dimension(180, 20));
		this.add(roomField);

		roomBtn = new JButton("Create");
		roomBtn.setEnabled(false);
		roomBtn.setPreferredSize(new Dimension(80, 20));
		roomBtn.addActionListener(this);
		roomBtn.setActionCommand("Create");
		this.add(roomBtn);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(385, 200));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);

		msgLabel = new JLabel("WRITE MESSAGE");
		msgLabel.setPreferredSize(new Dimension(110, 20));
		this.add(msgLabel);

		msgField = new JTextField();
		msgField.setEnabled(false);
		msgField.setPreferredSize(new Dimension(180, 20));
		this.add(msgField);

		msgBtn = new JButton("Send");
		msgBtn.setEnabled(false);
		msgBtn.setPreferredSize(new Dimension(80, 20));
		msgBtn.addActionListener(this);
		msgBtn.setActionCommand("Send");
		this.add(msgBtn);

		connectionManager = new SCCConnectionManager(this);
		currentRoom = new SCRoom();
	}

	public void writeMessage(String m) {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		textArea.append("[" + new Date(c.getTimeInMillis()) + " " + hour + ":"
				+ min + ":" + sec + "] " + m + "\n");
	}

	public void Announcement_writeMessage(String m) {
		announcementArea.append(m + "\n");
	}

	public void applyEnterRoom(SCRoom room) {
		if (currentRoom.getRoomNum() == -1) {
			connectionManager.send(new SCPacket("enterRoom",
					new Object[] { room }));
		}
	}

	public void setRoom(Vector<SCRoom> list) {
		roomList.setRoomList(list);
	}

	public void setRoomList(SCCRoomList roomList) {
		this.roomList = roomList;
	}

	public void enterRoom(SCRoom room) {
		currentRoom = new SCRoom(room);
		writeMessage(currentRoom.getRoomName() + " 방에 입장하셨습니다.");
		msgField.setEnabled(true);
		msgBtn.setEnabled(true);
	}

	public void exitRoom(SCRoom room) {
		writeMessage(currentRoom.getRoomName() + " 방에서 퇴장하셨습니다.");
		currentRoom = new SCRoom();
		msgField.setEnabled(false);
		msgBtn.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Connect")) {
			if (connectionManager.connectionServer(addressField.getText(),
					portField.getText())) {
				addressField.setEnabled(false);
				portField.setEnabled(false);
				connectBtn.setEnabled(false);
				roomField.setEnabled(true);
				roomBtn.setEnabled(true);
			}

		} else if (e.getActionCommand().equals("Create")) {
			connectionManager.send(new SCPacket("createRoom",
					new Object[] { roomField.getText() }));
		} else if (e.getActionCommand().equals("Send")) {
			connectionManager.send(new SCPacket("writeMessage",
					new Object[] { msgField.getText() }));
			msgField.setText("");
		}
	}

	public void applyExitRoom(SCRoom room) {
		// TODO Auto-generated method stub
		if (currentRoom.getRoomNum() == room.getRoomNum()) {
			connectionManager.send(new SCPacket("exitRoom",
					new Object[] { room }));
		}
	}
	
	public Boolean applyExitServer(){
		if(connectionManager.exitServer()){
			addressField.setEnabled(true);
			portField.setEnabled(true);
			connectBtn.setEnabled(true);
			roomField.setEnabled(false);
			roomBtn.setEnabled(false);
			return true;
		}
		return false;
	}
	
}
