package br.com.pegasus.module.toolkit.method;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.pegasus.module.toolkit.type.NumberType;
import br.com.pegasus.module.toolkit.type.StringType;

public final class CommonMethod {

	private static long index = -1;// atributo de 'getNextIndex()'

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

	/**
	 * Através da classe, é retornado uma abreviação do nome canônico dela.
	 * 
	 * <pre>
	 * Exemplo: A classe 'caminho.da.minha.classe.MinhaClasse' ficará 'c.d.m.c.MinhaClasse'
	 * </pre>
	 * 
	 * @param clazz A classe a ser trabalhada.
	 * @return Uma abreviação do nome canônico da classe.
	 */
	public static final String abbreviatesCanonicalName(Class<?> clazz) {
		String simpleName = clazz.getSimpleName();
		return Stream.of(clazz.getCanonicalName().split(StringType.SPLIT_PATH))
				.map(name -> simpleName.equals(name) ? name : name.charAt(NumberType.N_0) + StringType.PIPE_PATH)//
				.collect(Collectors.joining());//
	}

	/**
	 * Retorna o próximo número do índice da biblioteca. O indice é iniciado em -1.
	 * 
	 * @return retorna o próximo número do índice da biblioteca.
	 */
	public static long getNextIndex() {
		return ++index;
	}

	public static String timestampFormat(long timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(StringType.DATE_PATTERN)
				.withZone(ZoneId.systemDefault()); // Fuso horário padrão do sistema

		return formatter.format(Instant.ofEpochMilli(timestamp));
	}

}
