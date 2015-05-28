package object.shared;

import org.junit.Assert;
import org.junit.Test;

public class Test_SCRoom {

	@Test
	public void test_roomNum_get() {
		SCRoom scroom = new SCRoom();
		Assert.assertEquals(-1, scroom.getRoomNum());
	}
	@Test
	public void test_roomNum_set_get() {
		SCRoom scroom = new SCRoom();
		scroom.setRoomNum(10);
		Assert.assertEquals(10, scroom.getRoomNum());
	}
	@Test
	public void test_SCRoom_conductor() {
		SCRoom scroom = new SCRoom("Electronics",5);
		Assert.assertEquals(5, scroom.getRoomNum());
		Assert.assertEquals("Electronics", scroom.getRoomName());
		SCRoom current_room = new SCRoom(scroom);
		Assert.assertEquals("Electronics",current_room.getRoomName());
		Assert.assertEquals(5, current_room.getRoomNum());
	}
	@Test
	public void test_roomName_set_get() {
		SCRoom scroom = new SCRoom();
		scroom.setRoomName("Software engineering");
		Assert.assertEquals("Software engineering", scroom.getRoomName());
	}
}
