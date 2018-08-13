package model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "response")
public class GenericResponse {
	@XmlElement(name = "status")
	private boolean status;
	@XmlElement(name = "message")
	private String message;
	@XmlElement(name = "errorCode")
	private String errorCode;
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	@Override
	public String toString() {
		return status + "|" + message + "|" + errorCode;
	}
}
