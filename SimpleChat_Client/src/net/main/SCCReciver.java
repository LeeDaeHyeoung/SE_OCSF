package net.main;

import java.io.ObjectInputStream;
import java.util.Vector;

import object.shared.SCPacket;
import object.shared.SCRoom;
import ui.main.SCCMainPanel;

public class SCCReciver extends Thread implements Runnable {

	private ObjectInputStream objInputStream;
	private SCCMainPanel mainPanel;
	private Boolean stop = false;

	public SCCReciver(ObjectInputStream objInputStream, SCCMainPanel mainPanel) {
		super();
		this.objInputStream = objInputStream;
		this.mainPanel = mainPanel;
	}

	@SuppressWarnings("unchecked")
	public void run() {
		while (!stop) {
			SCPacket packet = null;
			try {
				packet = (SCPacket) objInputStream.readObject();
				if (packet.getMessage().equals("connectionSuccess")) {
					mainPanel.setRoom((Vector<SCRoom>) packet.getArgs()[0]);
					mainPanel.writeMessage(packet.getMessage());
				} else if (packet.getMessage().equals("setRoom")) {
					mainPanel.setRoom((Vector<SCRoom>) packet.getArgs()[0]);
				} else if (packet.getMessage().equals("enterRoom")) {
					mainPanel.enterRoom((SCRoom) packet.getArgs()[0]);
				} else if (packet.getMessage().equals("readMessage")) {
					mainPanel.writeMessage((String) packet.getArgs()[0]);
				} else if (packet.getMessage().equals("Annoucement")) {
					mainPanel.Announcement_writeMessage((String) packet
							.getArgs()[0]);
				} else if (packet.getMessage().equals("exitRoom")) {
					mainPanel.exitRoom((SCRoom) packet.getArgs()[0]);
				} else if (packet.getMessage().equals("Terminate")) {
					stop = true;
					mainPanel.applyExitServer();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
