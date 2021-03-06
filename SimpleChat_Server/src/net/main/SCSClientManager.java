package net.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
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
	private Boolean stop = false;

	private String[] message;
	private int count;
	
	public SCSClientManager(Socket socket, ServerManagerPanel mainPanel) {
		super();
		this.socket = socket;
		this.serverManager = mainPanel;

		message = new String[5];
		for(int i = 0; i<5;i++){
			message[i]="";
		}
		
		count=0;

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
		while (!stop) {
			try {
				SCPacket packet = (SCPacket) objInputStream.readObject();
				process(packet);
				serverManager.updateRoom();
			} catch (Exception e) {
				destroy();
			}
		}
	}

	private void process(SCPacket packet) {
		SCPacket returnPacket = new SCPacket();
		if (packet.getMessage().equals("applyConnection")) {
			returnPacket.setMessage("connectionSuccess");
			returnPacket.setArgs(new Object[] { serverManager.getRoomList() });
			serverManager.writeMessage(serverManager.getMessageArea(), socket
					.getInetAddress().getHostAddress() + " Connected");
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
				serverManager.enterClient(room, socket.getInetAddress()
						.getHostAddress());
			}
		} else if (packet.getMessage().equals("enterRoom")) {
			room = new SCRoom((SCRoom) packet.getArgs()[0]);
			try {
				returnPacket.setMessage("enterRoom");
				returnPacket.setArgs(new Object[] { room });
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverManager.enterClient(room, socket.getInetAddress()
					.getHostAddress());
		} else if (packet.getMessage().equals("writeMessage")) {
			serverManager.sendMessge(packet, room.getRoomNum());
			returnPacket.setMessage("writtenMessage");
			returnPacket.setArgs(new Object[] {});
		} else if (packet.getMessage().equals("exitRoom")) {
			try {
				returnPacket.setMessage("exitRoom");
				returnPacket.setArgs(new Object[] { room });
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverManager.exitClient(room, socket.getInetAddress().getHostAddress());
		}
	}

	public void sendMessage(String msg) {
		try {
			SCPacket returnPacket = new SCPacket();
			if(block(msg)){
				returnPacket.setMessage("blockMessage");
				returnPacket.setArgs(new Object[]{"채팅이 차단되었습니다."});
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			}
			else{
				returnPacket.setMessage("readMessage");
				returnPacket.setArgs(new Object[] { msg });
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Boolean block (String msg){
		Boolean equals = true;
		message[count] = msg;
		for(int i=0;i<5;i++){
			equals = message[count].equalsIgnoreCase(message[(count+1)%5]);
			equals &=equals;
		}
		count=(count+1)%5;
		return equals;
	}

	public void Announcement(String msg) {
		try {
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("Annoucement");
			returnPacket.setArgs(new Object[] { msg });
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getRoomNum() {
		return room.getRoomNum();
	}

	public String getRoomName() {
		return room.getRoomName();
	}

	public void setRoom(Vector<SCRoom> roomList) {
		try {
			Vector<SCRoom> list = new Vector<SCRoom>();
			list.addAll(roomList);
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("setRoom");
			returnPacket.setArgs(new Object[] { list });
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		stop = true;
		try {
			SCPacket returnPacket = new SCPacket();
			returnPacket.setMessage("Terminate");
			objOutputStream.writeObject(returnPacket);
			objOutputStream.flush();
			objInputStream.close();
			objOutputStream.close();
			socket.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		serverManager.writeMessage(serverManager.getMessageArea(),
				socket.getInetAddress() + " Disconnected");
		serverManager.clientClose(SCSClientManager.this);
		return;
	}

	public void stopManager() {
		stop = true;
	}

	public InetAddress getClientAddress() {
		return socket.getLocalAddress();
	}
}
