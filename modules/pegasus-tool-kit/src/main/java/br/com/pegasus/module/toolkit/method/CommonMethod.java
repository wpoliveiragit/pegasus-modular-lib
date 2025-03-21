package br.com.pegasus.module.toolkit.method;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.pegasus.module.toolkit.type.NumberType;
import br.com.pegasus.module.toolkit.type.StringType;

public final class CommonMethod {

	private static long index = -1;// atributo de 'getNextIndex()'

	/**
	 * Retorna o próximo número do índice da biblioteca. O indice é iniciado em -1.
	 * 
	 * @return retorna o próximo número do índice da biblioteca.
	 */
	public static long getNextIndex() {
		return ++index;
	}

	/**
	 * Converte Array de string em array de int.
	 * 
	 * @param values Array de string.
	 * @return array de int.
	 */
	public static final int[] toArrayInt(String... values) {
		return Stream.of(values).mapToInt(Integer::parseInt).toArray();
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

}
