package net.main;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;

public class Test_ServerManagerPanel {
	//���� : ���� �ð��� writeMessage���� ���Ƿ� test�Ҷ��� �ð��̶� �ٸ��� �ȴ�.
	@Test
	public void test_writeMessage() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.writeMessage(servermanagerpanel.getMessageArea(),"server start");
		Assert.assertEquals(servermanagerpanel.getMessageArea(),"server start");
	}
	@Test
	public void test_room_writeMessage() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.room_writeMessage("SE class");
		Assert.assertEquals(servermanagerpanel.getRoom_messageArea(),"SE class");
	}
	@Test
	public void test_client_writeMessage() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.client_writeMessage("Lee Dae Hyeoung");
		Assert.assertEquals(servermanagerpanel.getClient_messageArea(),"Lee Dae Hyeoung");
	}
	@Test
	public void test_Announcement() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.actionPerformed(new ActionEvent(1, 0, "ServerStart"));
		Assert.assertEquals(servermanagerpanel.getServerControlBtnMsg(),"erver terminate");
		//To Do confirm message
	}
	@Test
	public void test_ServerStart() {
		ServerManagerPanel serverManagerPanel = new ServerManagerPanel();
		serverManagerPanel.startServer();
	}
}