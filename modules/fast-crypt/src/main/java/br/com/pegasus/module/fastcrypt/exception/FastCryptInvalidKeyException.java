package br.com.pegasus.module.fastcrypt.exception;

public class FastCryptInvalidKeyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FastCryptInvalidKeyException(String message) {
		super(message);
	}

}
