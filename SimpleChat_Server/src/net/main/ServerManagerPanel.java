package net.main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerManagerPanel extends JPanel implements ActionListener, Runnable {

	
	private static final long serialVersionUID = 1L;

	private JLabel serverstateLabel;
	private JLabel servertimeLabel;

	private JLabel infoLabel;
	private JLabel portLabel;
	private JTextField portField;
	
	private JButton connectBtn;
	
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
	
	public ServerManagerPanel(){
		super(new FlowLayout(FlowLayout.LEFT,8,4));
		this.setPreferredSize(new Dimension(1000, 700));
		serverstateLabel = new JLabel(" ServerState : ");
		serverstateLabel.setPreferredSize(new Dimension(150,20));
		this.add(serverstateLabel);
		
		servertimeLabel = new JLabel(" ServerTime : ");
		servertimeLabel.setPreferredSize(new Dimension(230,20));
		this.add(servertimeLabel);
		
		infoLabel = new JLabel(" ServerAddress : ");
		infoLabel.setPreferredSize(new Dimension(200,20));
		this.add(infoLabel);
		
		portLabel = new JLabel(" Port : ");
		portLabel.setPreferredSize(new Dimension(40,20));
		this.add(portLabel);
		
		portField = new JTextField("8000");
		portField.setPreferredSize(new Dimension(100,20));
		this.add(portField);
		
		connectBtn = new JButton("Connect");
		connectBtn.setPreferredSize(new Dimension(80,20));
		connectBtn.addActionListener(this);
		connectBtn.setActionCommand("Connect");
		this.add(connectBtn);
		
		messageArea = new JTextArea();
		messageArea.setEditable(false);
		scrollPane = new JScrollPane(messageArea);
		scrollPane.setPreferredSize(new Dimension(400,600));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane);
		
		room_messageArea = new JTextArea();
		room_messageArea.setEditable(false);
		room_scrollPane = new JScrollPane(room_messageArea);
		room_scrollPane.setPreferredSize(new Dimension(270,600));
		room_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(room_scrollPane);
		
		client_messageArea = new JTextArea();
		client_messageArea.setEditable(false);
		client_scrollPane = new JScrollPane(client_messageArea);
		client_scrollPane.setPreferredSize(new Dimension(270,600));
		client_scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(client_scrollPane);
		
		announcementLabel = new JLabel(" Announcement : ");
		announcementLabel.setPreferredSize(new Dimension(101,20));
		this.add(announcementLabel);
		
		msgField = new JTextField();
		msgField.setEnabled(false);
		msgField.setPreferredSize(new Dimension(480,20));
		this.add(msgField);
		
		msgBtn = new JButton("Send");
		msgBtn.setEnabled(false);
		msgBtn.setPreferredSize(new Dimension(80,20));
		msgBtn.addActionListener(this);
		msgBtn.setActionCommand("Send");
		this.add(msgBtn);
	}
	public void writeMessage(String m){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		messageArea.append("["+new Date(c.getTimeInMillis())+" "+hour+":"+min+":"+sec+"] "+m+"\n");
	}
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*messageArea값을 확인하기위해서 
	 * test를 하기위해서 getter정의 
	 */
	public String getMessageArea() {
		return messageArea.toString();
	}
}
