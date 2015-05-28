package net.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import object.shared.SCPacket;
import object.shared.SCRoom;

public class SCSClientManager extends Thread implements Runnable {
	private Socket socket;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;

	private ServerManagerPanel serverManager;

	private SCRoom room;

	public SCSClientManager(Socket socket, ServerManagerPanel mainPanel) {
		super();
		this.socket = socket;
		this.serverManager = mainPanel;

		room = new SCRoom();

		prepareConnect();
	}

	private void prepareConnect() {
		try {
			objInputStream = new ObjectInputStream(socket.getInputStream());
			objOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				SCPacket packet = (SCPacket) objInputStream.readObject();
				process(packet);
				serverManager.run();
			} catch (Exception e) {
				try {
					serverManager.writeMessage(socket.getInetAddress()
							+ " Disconnected");
					objInputStream.close();
					objOutputStream.close();
					socket.close();
					return;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	private void process(SCPacket packet) {
		SCPacket returnPacket = new SCPacket();
		if (packet.getMessage().equals("applyConnection")) {
			returnPacket.setMessage("connectionSuccess");
			returnPacket.setArgs(new Object[] { serverManager.getRoomList() });
			serverManager.writeMessage(socket.getInetAddress().getHostAddress()
					+ " Connected");
			try {
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (packet.getMessage().equals("createRoom")) {
			room = new SCRoom(serverManager.createRoom((String) packet
					.getArgs()[0]));
			try {
				returnPacket.setMessage("enterRoom");
				returnPacket.setArgs(new Object[] { room });
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
				serverManager.enterClient(room, socket.getInetAddress().getHostAddress());
			}
		}
	}
	
	public void sendMessage(String msg){
		try {
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("readMessage");
			returnPacket.setArgs(new Object[]{msg});
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Announcement(String msg){
		try {
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("Annoucement");
			returnPacket.setArgs(new Object[]{msg});
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getRoonNum() {
		return room.getRoomNum();
	}

	
	public void setRoom(Vector<SCRoom> roomList) {
		try {
			Vector<SCRoom> list = new Vector<SCRoom>();
			list.addAll(roomList);
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("setRoom");
			returnPacket.setArgs(new Object[]{list});
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
