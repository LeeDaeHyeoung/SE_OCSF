
public class RoomClass {
	String[] clientList;
	String chief, roomName, roomPw;
	String[] lastConv;	//Last Conversation
	
	RoomClass( String chiefId, String roomname, String roompw){
		this.chief = chiefId;
		this.roomName = roomname;
		this.roomPw = roompw;
	}
	
	
	//Client List Set & Get
	void setClientList(String[] clientlist){
		this.clientList = clientlist;
	}
	
	String[] getClientList(){
		return clientList;
	}
	
	//Chief Set & Get
	void setChief(String tchief){
		this.chief = tchief;
	}
	
	String getChief(){
		return chief;
	}
	
	//Room Name Set & Get
	void setRoomName(String name){
		this.roomName = name;
	}
	
	String getRoomName(){
		return roomName;
	}
	
	//Room Password List Set & Get
	void setRoomPw(String pw){
		this.roomPw = pw;
	}
	
	String getRoomPw(){
		return roomPw;
	}
	
	//Last Conversation Set & Get
	void setLasConv(String[] lastconv){
		this.lastConv = lastconv;
	}
	
	String[] getLastConv(){
		return lastConv;
	}
	
}
