package object.shared;

//자바 객체를 저장 또는 전송을 위하여 자바 코드를 다시 복원 가능한 byte stream 형태로 변환.
import java.io.Serializable;

public class SCPacket implements Serializable {
	
	//객체 데이터를 수신한 측에서 class명이 동일하더라도 이것을 통하여 동일한 버전의 class인지를 확인.
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Object[] args;
	
	public SCPacket(){}
	
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
