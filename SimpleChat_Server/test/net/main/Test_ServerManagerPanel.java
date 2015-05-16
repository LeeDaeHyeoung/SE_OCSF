package net.main;

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

}