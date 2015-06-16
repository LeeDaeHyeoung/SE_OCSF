package net.main;

import static org.junit.Assert.*;
import object.shared.SCPacket;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ui.main.SCCMainPanel;

public class Test_SCCConnectionManager {

	@Test
	public void test_connectionServer() {
		SCCConnectionManager connectionManager = new SCCConnectionManager(
				new SCCMainPanel());
		assertEquals(connectionManager.connectionServer("123", "23"), false);
	}

	@Test
	public void test_send() {
		SCCConnectionManager connectionManager = new SCCConnectionManager(
				new SCCMainPanel());
		SCPacket packet = new SCPacket();
		assertEquals(connectionManager.send(packet), false);
	}

	@Test
	public void test_exitServer() {
		SCCConnectionManager connectionManager = new SCCConnectionManager(
				new SCCMainPanel());

		assertEquals(connectionManager.exitServer(), false);
	}

}
