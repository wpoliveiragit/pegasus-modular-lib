package br.com.pegasus.module.fastcrypt.consts;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class FastCryptConsts {

	public static final String ALGORITHM;

	public static final List<Integer> KEY_SIZES;

	public static final String MSG_KEY_NULL_ERROR;
	public static final String MSG_INVALID_KEY_SIZE_ERROR;

	static {
		ALGORITHM = "AES";

		KEY_SIZES = Arrays.asList(10, 11, 12, 16, 17, 18, 22, 23, 24);

		MSG_KEY_NULL_ERROR = "A chave está nula";

		MSG_INVALID_KEY_SIZE_ERROR = "Tamanho de chave inválido :: Tamanhos permitidos: "
				+ KEY_SIZES.stream().map(String::valueOf).collect(Collectors.joining(", "));
	}

}
