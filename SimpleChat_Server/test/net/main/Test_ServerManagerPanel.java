package net.main;

import org.junit.Assert;
import org.junit.Test;

public class Test_ServerManagerPanel {
	//���� : ���� �ð��� writeMessage���� ���Ƿ� test�Ҷ��� �ð��̶� �ٸ��� �ȴ�.
	@Test
	public void test_writeMessage() {
		ServerManagerPanel servermanagerpanel = new ServerManagerPanel();
		servermanagerpanel.writeMessage("server start");
		Assert.assertEquals(servermanagerpanel.getMessageArea(),"server start");
	}

}