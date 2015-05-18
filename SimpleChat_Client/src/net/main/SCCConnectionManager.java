package net.main;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	public void connectionServer(String address,String string){
		try {
			socket = new Socket(address, Integer.parseInt(string));
			objOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objInputStream = new ObjectInputStream(socket.getInputStream());
			objOutputStream.writeObject(new SCPacket("applyConnection",new Object[]{}));
			objOutputStream.flush();
			reciver = new SCCReciver(objInputStream,mainPanel);
			reciver.start();
		} catch (Exception e) {
			e.printStackTrace();
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
}
