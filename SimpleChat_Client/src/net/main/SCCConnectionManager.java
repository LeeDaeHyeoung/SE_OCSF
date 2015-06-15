package net.main;

import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import object.shared.SCPacket;
import ui.main.SCCMainPanel;

public class SCCConnectionManager {
	private Socket socket;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	
	private SCCMainPanel mainPanel;
	private SCCReciver reciver;
	
	public SCCConnectionManager(SCCMainPanel mainPanel) {
		super();
		this.mainPanel = mainPanel;
	}
	public Boolean connectionServer(String address,String string){
		SocketAddress socketAddress = new InetSocketAddress(address,Integer.parseInt(string));
		socket = new Socket();
		int timeout = 3000;
		try {
			socket.connect(socketAddress,timeout);
			objOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objInputStream = new ObjectInputStream(socket.getInputStream());
			objOutputStream.writeObject(new SCPacket("applyConnection",new Object[]{}));
			objOutputStream.flush();
			reciver = new SCCReciver(objInputStream,mainPanel);
			reciver.start();
			return true;
		} catch (Exception e) {
			mainPanel.writeMessage("Server Connction Failed");
			return false;
		}
	}
	public void send(SCPacket packet) {
		try {
		objOutputStream.writeObject(packet);
		objOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Boolean exitServer(){
		try {
			objInputStream.close();
			objOutputStream.close();
			socket.close();
			mainPanel.writeMessage("Server Connction termination");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
