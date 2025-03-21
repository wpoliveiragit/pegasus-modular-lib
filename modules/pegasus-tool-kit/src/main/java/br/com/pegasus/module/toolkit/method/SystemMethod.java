package br.com.pegasus.module.toolkit.method;

import java.awt.Toolkit;

import br.com.pegasus.module.toolkit.type.NumberType;

public class SystemMethod {

	/** * Emite um beep */
	public static final void bip() {
		Toolkit.getDefaultToolkit().beep();
	}

	/**
	 * Retorna a classe corrente onde o objeto foi criado.
	 * 
	 * @return A classe corrente onde o objeto foi criado.
	 */
	public static final Class<?> captureCreatorClass() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace.length > NumberType.N_3) {
			try {
				return Class.forName(stackTrace[NumberType.N_3].getClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
