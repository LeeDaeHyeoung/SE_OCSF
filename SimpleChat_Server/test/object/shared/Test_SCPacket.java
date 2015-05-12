package object.shared;

import org.junit.Assert;
import org.junit.Test;

public class Test_SCPacket {


	@Test
	public void test_message_conductor(){
		Object[] test_object = new Object[5];
		SCPacket scpacket = new SCPacket("daehyeoung",test_object);
		Assert.assertEquals("daehyeoung",scpacket.getMessage());
	}
	@Test
	public void test_message_get() {
		SCPacket scpacket = new SCPacket();
		scpacket.setMessage("daehyeoung");
		Assert.assertEquals("daehyeoung",scpacket.getMessage());
	}
	@Test
	public void test_args_get(){
		Object[] test_object = new Object[5];
		SCPacket scpacket = new SCPacket();
		scpacket.setArgs(test_object);
		Assert.assertArrayEquals(test_object,scpacket.getArgs());
	}
	
}
