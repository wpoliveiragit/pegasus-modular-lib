package module.exception.monitor.v1.project;

import lombok.Getter;

public class AnyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private String msg;

	public AnyException(String message) {
		super(message);
	}
	public AnyException(String message, String msg) {
		super(message);
		this.msg = msg;
	}
}
