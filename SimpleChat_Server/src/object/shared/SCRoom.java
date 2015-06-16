package object.shared;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

public class SCRoom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String roomName;
	private int roomNum;
	private String roomStartTime;
	
	public SCRoom() {
		roomNum = -1;
		setRoomTime();
	}

	public SCRoom(String roomName, int roomNum) {
		this.roomName = roomName;
		this.roomNum = roomNum;
		setRoomTime();
	}
	
	public SCRoom(SCRoom room) {
		this.roomName = room.getRoomName();
		this.roomNum = room.getRoomNum();
		this.roomStartTime = room.getRoomStartTime();
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
	
	public void setRoomTime(){
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		roomStartTime = "[" + new Date(c.getTimeInMillis()) + " " + hour
				+ ":" + min + ":" + sec + "] ";
	}
	
	public String getRoomStartTime(){
		return roomStartTime;
	}
	
}
