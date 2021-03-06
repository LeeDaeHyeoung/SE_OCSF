package net.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import object.shared.SCPacket;
import object.shared.SCRoom;

public class ServerManagerPanel extends JPanel implements ActionListener,
		Runnable {

	private static final long serialVersionUID = 1L;

	private JLabel serverstateLabel;
	private JLabel servertimeLabel;

	private JLabel infoLabel;
	private JLabel portLabel;
	private JTextField portField;

	private JButton serverControlBtn;

	private JTextArea messageArea;

	private JScrollPane scrollPane;

	private JTextArea room_messageArea;
	private JScrollPane room_scrollPane;

	private JTextArea client_messageArea;
	private JScrollPane client_scrollPane;

	private JLabel announcementLabel;
	private JTextField msgField;
	private JButton msgBtn;

	private ServerSocket sSocket;
	private Vector<SCRoom> roomList;
	private Vector<SCSClientManager> clientList;

	private Thread connectThread;
	private Boolean stop = false;

	public ServerManagerPanel() {
		super(new FlowLayout(FlowLayout.LEFT, 8, 4));
		this.setPreferredSize(new Dimension(1000, 700));

		clientList = new Vector<SCSClientManager>();
		roomList = new Vector<SCRoom>();

		serverstateLabel = new JLabel(" ServerState : ");
		serverstateLabel.setPreferredSize(new Dimension(150, 20));
		this.add(serverstateLabel);

		servertimeLabel = new JLabel(" ServerTime : ");
		servertimeLabel.setPreferredSize(new Dimension(230, 20));
		this.add(servertimeLabel);

		infoLabel = new JLabel(" ServerAddress : ");
		infoLabel.setPreferredSize(new Dimension(200, 20));
		this.add(infoLabel);

		portLabel = new JLabel(" Port : ");
		portLabel.setPreferredSize(new Dimension(40, 20));
		this.add(portLabel);

		portField = new JTextField("8000");
		portField.setPreferredSize(new Dimension(100, 20));
		this.add(portField);

		serverControlBtn = new JButton("Server start");
		serverControlBtn.setPreferredSize(new Dimension(140, 20));
		serverControlBtn.addActionListener(this);
		serverControlBtn.setActionCommand("ServerStart");
		this.add(serverControlBtn);

		messageArea = new JTextArea();
		messageArea.setEditable(false);
		scrollPane = new JScrollPane(messageArea);
		scrollPane.setPreferredSize(new Dimension(400, 600));
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);

		room_messageArea = new JTextArea();
		room_messageArea.setEditable(false);
		room_scrollPane = new JScrollPane(room_messageArea);
		room_scrollPane.setPreferredSize(new Dimension(270, 600));
		room_scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(room_scrollPane);

		client_messageArea = new JTextArea();
		client_messageArea.setEditable(false);
		client_scrollPane = new JScrollPane(client_messageArea);
		client_scrollPane.setPreferredSize(new Dimension(270, 600));
		client_scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(client_scrollPane);

		announcementLabel = new JLabel(" Announcement : ");
		announcementLabel.setPreferredSize(new Dimension(101, 20));
		this.add(announcementLabel);

		msgField = new JTextField();
		msgField.setEnabled(false);
		msgField.setPreferredSize(new Dimension(480, 20));
		this.add(msgField);

		msgBtn = new JButton("Send");
		msgBtn.setEnabled(false);
		msgBtn.setPreferredSize(new Dimension(80, 20));
		msgBtn.addActionListener(this);
		msgBtn.setActionCommand("Send");
		this.add(msgBtn);
	}

	// JTextArea창에 쓰고자하는 메세지를 담당하는 기능 구현
	public void writeMessage(JTextArea area, String m) {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		area.append("[" + new Date(c.getTimeInMillis()) + " " + hour
				+ ":" + min + ":" + sec + "] " + m + "\n");
	}

	// room Area창에 현재 존재하는 방을 만들어 주기위한 메소드
	public void room_writeMessage(Vector<SCRoom> roomList) {
		room_messageArea.append(roomList.toString() + "\n");
	}

	public void room_writeMessage(String m) {
		room_messageArea.append(m + "\n");
	}

	// client Area창에 현재 접속해 있는 모든 사람들을 표시해주기위한 메소드
	public void updateClients() {
		client_messageArea.setText("Current Client\n");
		for(SCSClientManager tempList : clientList){
			client_messageArea.append(tempList.getClientAddress() + "\n");
		}
	}
	public void updateRoom(){
		room_messageArea.setText("Current Room\n");
		for(SCRoom temproom : roomList){
			room_messageArea.append(temproom.getRoomName()+"\n");
		}
	}
	public void client_writeMessage(String m) {
		client_messageArea.append(m + "\n");
	}

	public void startServer() {
		connectThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sSocket = new ServerSocket(Integer.valueOf(portField
							.getText()), 10);
					writeMessage(messageArea,"Server Started");
					updateRoom();
					updateClients();
					infoLabel.setText(" ServerAddress : "
							+ InetAddress.getLocalHost().getHostAddress());
					while (!stop) {
						updateRoom();
						updateClients();
						Socket socket;
						socket = sSocket.accept();
						SCSClientManager clientManager = new SCSClientManager(
								socket, ServerManagerPanel.this);
						clientManager.start();
						clientList.add(clientManager);
						writeMessage(client_messageArea, clientManager.getRoomName());
					}
				} catch (IOException e) {
					terminateServer();
				}

			}
		});
		connectThread.start();
	}

	public void terminateServer() {
		stop = true;
		while (!clientList.isEmpty()) {
			SCSClientManager temp = clientList.firstElement();
			temp.stopManager();
			temp.destroy();
			clientList.remove(0);
		}
		updateClients();
		client_writeMessage("All clients is Disconnected");
		roomList.removeAllElements();
		room_writeMessage("Terminated All Room");
		writeMessage(messageArea,"Server Terminated");
		infoLabel.setText(" ServerAddress : ");
	}

	public Vector<SCRoom> getRoomList() {
		Vector<SCRoom> list = new Vector<SCRoom>();
		list.addAll(roomList);
		return list;
	}

	public SCRoom createRoom(String roomName) {
		for(SCRoom scRoom : roomList){
			if(roomName == scRoom.getRoomName())
				return scRoom;
		}
		
		SCRoom room = new SCRoom(roomName, roomList.size());
		roomList.add(room);
		for (SCSClientManager clientManager : clientList) {
			clientManager.setRoom(getRoomList());
		}
		return room;
	}

	public void enterClient(SCRoom room, String address) {
		for (SCSClientManager clientManager : clientList) {
			if (clientManager.getRoomNum() == room.getRoomNum()) {
				clientManager.sendMessage(address + " 가 입장하였습니다.");
			}
		}
	}

	public void exitClient(SCRoom room, String address) {
		for (SCSClientManager clientManager : clientList) {
			if ((clientManager.getRoomNum() == room.getRoomNum())
					&& (clientManager.getRoomNum() != -1)) {
				clientManager.sendMessage(address + " 가 퇴장하였습니다.");
			}
		}
		room.setRoomNum(-1);
	}

	public void announcement_Allclient() {
		for (SCSClientManager clientManager : clientList) {
			clientManager.Announcement("Announcement : " + msgField.getText());
		}
	}

	public void sendMessge(SCPacket packet, int roomNum) {
		for (SCSClientManager clientManager : clientList) {
			if (clientManager.getRoomNum() == roomNum) {
				System.out.println(clientManager.getRoomNum());
				clientManager.sendMessage((String) packet.getArgs()[0]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("ServerStart")) {
			stop = false;
			serverstateLabel.setText(" ServerState : Running");
			portField.setEnabled(false);
			serverControlBtn.setText("Server terminate");
			serverControlBtn.setActionCommand("ServerTerminate");
			msgField.setEnabled(true);
			msgBtn.setEnabled(true);
			startServer();
		} else if (arg0.getActionCommand().equals("Send")) {
			announcement_Allclient();
			writeMessage(messageArea,msgField.getText());
			msgField.setText("");
		} else if (arg0.getActionCommand().equals("ServerTerminate")) {
			try {
				sSocket.close();
				serverstateLabel.setText(" ServerState : Terminated");
				portField.setEnabled(true);
				serverControlBtn.setText("Server Start");
				serverControlBtn.setActionCommand("ServerStart");
				msgField.setEnabled(false);
				msgBtn.setEnabled(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	/*
	 * messageArea값을 확인하기위해서 test를 하기위해서 getter정의
	 */
	public JTextArea getMessageArea() {
		return messageArea;
	}

	/*
	 * room messageArea값을 확인하기위해서 test를 하기위해서 getter정의
	 */
	public JTextArea getRoom_messageArea() {
		return room_messageArea;
	}
	
	public JTextArea getclient_messageArea(){
		return client_messageArea;
	}

	/*
	 * client messageArea값을 확인하기위해서 test를 하기위해서 getter정의
	 */
	public String getClient_messageArea() {
		return client_messageArea.toString();
	}

	// Server 가동현황을 확인하는 test case에서 사용
	public String getServerControlBtnMsg() {
		return serverControlBtn.getText();
	}
	
	public void clientClose(SCSClientManager client){
		for(SCSClientManager mClient : clientList)
			if (mClient == client) {
				clientList.remove(client);
				updateClients();
			}
	}
}
