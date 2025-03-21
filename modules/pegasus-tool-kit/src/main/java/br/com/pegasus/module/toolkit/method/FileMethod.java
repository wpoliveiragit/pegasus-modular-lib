package br.com.pegasus.module.toolkit.method;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import br.com.pegasus.module.toolkit.exception.ErrorFileException;

public final class FileMethod {

	/**
	 * Busca um arquivo no diretório base 'src/main/resources'. Caso use este método
	 * em ambiente de teste, o diretório base muda para 'src/test/resources'.
	 * 
	 * @param pathName O caminho com o nome do arquivo que esta em resources.
	 * @return O conteudo do arquivo.
	 * @throws Exception Caso o arquivo esteja com problemas de leitura ou não
	 *                   exista.
	 */
	public static String readFileFromResources(final String pathName) throws Exception {
		try {
			InputStream inputStream = CommonMethod.class.getClassLoader().getResourceAsStream(pathName);
			if (inputStream == null) {
				throw new RuntimeException("Arquivo não encontrado: " + pathName);
			}
			try {
				return Files.readString(Paths.get(Paths.get(inputStream.toString()).toString()));
			} catch (Exception ex) {
				throw new RuntimeException("Problemas ao ler o arquivo: " + pathName);
			}
		} catch (Exception exc) {
			throw new ErrorFileException(exc);
		}
	}

}
