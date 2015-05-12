package object.shared;

import java.io.Serializable;

public class SCRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String roomName;
	private int roomNum;
	
	public SCRoom() {
		roomNum = -1;
	}

	public SCRoom(String roomName, int roomNum) {
		this.roomName = roomName;
		this.roomNum = roomNum;
	}
	
	public SCRoom(SCRoom room) {
		this.roomName = room.getRoomName();
		this.roomNum = room.getRoomNum();
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	
	public String toString(){
		return roomName;
	}
	
}
