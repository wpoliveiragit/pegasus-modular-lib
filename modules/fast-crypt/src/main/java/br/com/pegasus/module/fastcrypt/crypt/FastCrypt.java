package br.com.pegasus.module.fastcrypt.crypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import br.com.pegasus.module.fastcrypt.consts.FastCryptConsts;
import br.com.pegasus.module.fastcrypt.exception.FastCryptCipherException;
import br.com.pegasus.module.fastcrypt.exception.FastCryptInvalidKeyException;

/**
 * A classe <code>FastCrypt</code> fornece funcionalidades de criptografia e
 * descriptografia usando uma chave secreta. Utiliza o algoritmo de criptografia
 * especificado em {@link FastCryptConsts#ALGORITHM} para criptografar e
 * descriptografar strings. As chaves usadas devem ter um tamanho válido,
 * conforme especificado em {@link FastCryptConsts#KEY_SIZES}.
 * 
 * <p>
 * Esta classe usa a codificação Base64 para representar os dados criptografados
 * como strings legíveis.
 * </p>
 * 
 * @author Wellington Pires de Oliveira
 * @version 1.0
 */
public class FastCrypt {

	private Cipher encrypt = null;
	private Cipher decrypt = null;

	/**
	 * Construtor da classe <code>FastCrypt</code>. Inicializa os objetos de
	 * criptografia e descriptografia com base na chave fornecida. A chave é
	 * verificada quanto à sua validade e transformada para um formato adequado.
	 *
	 * @param keyValue A chave secreta usada para criptografia/descriptografia. Não
	 *                 pode ser <code>null</code> nem ter um tamanho inválido.
	 * @throws FastCryptInvalidKeyException Se a chave for <code>null</code> ou
	 *                                      tiver um tamanho inválido.
	 * @throws Exception                    Se ocorrer algum erro ao criar os
	 *                                      objetos <code>Cipher</code>.
	 */
	public FastCrypt(String keyValue) throws Exception {
		if (keyValue == null) {
			throw new FastCryptInvalidKeyException(FastCryptConsts.MSG_KEY_NULL_ERROR);
		}

		if (!FastCryptConsts.KEY_SIZES.contains(keyValue.length())) {
			throw new FastCryptInvalidKeyException(FastCryptConsts.MSG_INVALID_KEY_SIZE_ERROR);
		}

		keyValue = new String(Base64.encodeBase64(keyValue.getBytes()));
		Key key = new SecretKeySpec(keyValue.getBytes(), FastCryptConsts.ALGORITHM);
		decrypt = createCipher(key, Cipher.DECRYPT_MODE);
		encrypt = createCipher(key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * Criptografa um texto utilizando a chave secreta fornecida.
	 *
	 * @param text O texto a ser criptografado.
	 * @return A string criptografada, representada em Base64.
	 * @throws Exception Se ocorrer algum erro durante o processo de criptografia.
	 */
	public String encrypt(String text) throws Exception {
		byte[] encodeBase64 = Base64.encodeBase64(encrypt.doFinal(text.getBytes()));
		return new String(encodeBase64);
	}

	/**
	 * Descriptografa um texto previamente criptografado.
	 *
	 * @param text O texto criptografado, em formato Base64.
	 * @return O texto original, após a descriptografia.
	 * @throws Exception Se ocorrer algum erro durante o processo de
	 *                   descriptografia.
	 */
	public String decript(String text) throws Exception {
		byte[] doFinal = decrypt.doFinal(Base64.decodeBase64(text));
		return new String(doFinal);
	}

	/**
	 * Cria um objeto <code>Cipher</code> configurado para criptografar ou
	 * descriptografar dados.
	 * 
	 * @param key       A chave secreta usada para inicializar o
	 *                  <code>Cipher</code>.
	 * @param cryptMode O modo de operação do <code>Cipher</code> (criptografia ou
	 *                  descriptografia).
	 * @return O objeto <code>Cipher</code> configurado.
	 * @throws Exception Se ocorrer algum erro ao inicializar o <code>Cipher</code>.
	 */
	private static Cipher createCipher(Key key, int cryptMode) {
		try {
			Cipher cipher = Cipher.getInstance(FastCryptConsts.ALGORITHM);
			cipher.init(cryptMode, key);
			return cipher;
		} catch (Exception ex) {
			throw new FastCryptCipherException(ex);
		}
	}
}
