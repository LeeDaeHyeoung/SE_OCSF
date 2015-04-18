import java.sql.Time;

public class ConvNode {
	String id, conv, time;
	ConvNode nextNode;

	ConvNode(String id, String conv, String time) {
		this.id = id;
		this.conv = conv;
		this.time = time;
		nextNode = null;
	}

	ConvNode(String id, String conv, String time, ConvNode next) {
		this.id = id;
		this.conv = conv;
		this.time = time;
		this.nextNode = next;
	}
}
