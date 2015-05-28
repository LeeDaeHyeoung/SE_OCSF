package net.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import object.shared.SCPacket;
import object.shared.SCRoom;

public class SCSClientManager extends Thread implements Runnable{
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
	
	private void prepareConnect(){
		try {
			objInputStream = new ObjectInputStream(socket.getInputStream());
			objOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(true){
			try {
				SCPacket packet = (SCPacket)objInputStream.readObject();
				process(packet);
				serverManager.run();
			} catch (Exception e) {
				try {
					serverManager.writeMessage(socket.getInetAddress()+" Disconnected");
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
		if(packet.getMessage().equals("applyConnection")){
			returnPacket.setMessage("connectionSuccess");
			returnPacket.setArgs(new Object[]{serverManager.getRoomList()});
			serverManager.writeMessage(socket.getInetAddress().getHostAddress()+" Connected");
			try {
				objOutputStream.writeObject(returnPacket);
				objOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
		
}
