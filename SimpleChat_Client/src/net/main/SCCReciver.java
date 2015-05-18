package net.main;

import java.io.ObjectInputStream;
import java.util.Vector;

import object.shared.SCPacket;
import object.shared.SCRoom;
import ui.main.SCCMainPanel;

public class SCCReciver extends Thread implements Runnable{

	private ObjectInputStream objInputStream;
	private SCCMainPanel mainPanel;

	public SCCReciver(ObjectInputStream objInputStream, SCCMainPanel mainPanel) {
		super();
		this.objInputStream = objInputStream;
		this.mainPanel = mainPanel;
	}
	
	@SuppressWarnings("unchecked")
	public void run(){
		while(true){
			SCPacket packet = null;
			try{
				packet = (SCPacket)objInputStream.readObject();
				if(packet.getMessage().equals("connectionSuccess")){
					mainPanel.setRoom((Vector<SCRoom>)packet.getArgs()[0]);
					mainPanel.writeMessage(packet.getMessage());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
