package br.com.pegasus.module.fastcrypt.consts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FastCryptConsts {

	public static final List<Integer> KEY_SIZES;
	public static final String MSG_INVALID_SIZE_ERRO;
	public static final String ALGORITHM = "AES";

	public static final String MSG_NULL_POITER = "Chave de criação do modulo de critografia nula";

	static {
		KEY_SIZES = Arrays.asList(10, 11, 12, 16, 17, 18, 22, 23, 24);

		MSG_INVALID_SIZE_ERRO = "Tamanho da chave de criação do modulo de critografia invalido. Tamanhos aceitáveis: "//
				+ KEY_SIZES.stream().map(String::valueOf) //
						.collect(Collectors.joining(", "));
	}

}
