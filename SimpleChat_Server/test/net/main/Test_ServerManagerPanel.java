package net.main;

import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;

public class Test_ServerManagerPanel {
	//문제 : 현재 시간을 writeMessage에서 찍어내므로 test할때의 시간이랑 다르게 된다.
	@Test
	public void test_writeMessage() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.writeMessage("server start");
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
		servermanagerpanel.actionPerformed(new ActionEvent(1, 0, "Connect"));
		servermanagerpanel.actionPerformed(new ActionEvent(1, 0, "Send"));
		//To Do confirm message
	}
}