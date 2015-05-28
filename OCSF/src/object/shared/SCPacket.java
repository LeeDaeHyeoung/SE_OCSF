package object.shared;

import java.io.Serializable;

public class SCPacket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private Object[] args;
	
	public SCPacket() {}
	
	public SCPacket(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
