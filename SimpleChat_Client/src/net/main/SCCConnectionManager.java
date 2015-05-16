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
}
