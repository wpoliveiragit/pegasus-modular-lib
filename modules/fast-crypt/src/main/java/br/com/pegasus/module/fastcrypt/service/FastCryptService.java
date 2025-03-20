package br.com.pegasus.module.fastcrypt.service;

import java.security.Key;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;

import br.com.pegasus.module.fastcrypt.consts.FastCryptConsts;
import br.com.pegasus.module.fastcrypt.exception.InvalidSizeFastCryptException;
import br.com.pegasus.module.fastcrypt.exception.NullPointerFastCryptException;
import br.com.pegasus.module.fastcrypt.method.FastCryptMethod;

public class FastCryptService {

	private Cipher encrypt = null;
	private Cipher decrypt = null;

	public FastCryptService(String key) {
		if (key == null) {
			throw new NullPointerFastCryptException(FastCryptConsts.MSG_NULL_POITER);
		}
		if (!FastCryptConsts.KEY_SIZES.contains(key.length())) {
			throw new InvalidSizeFastCryptException(FastCryptConsts.MSG_INVALID_SIZE_ERRO);
		}
		Key secretKey = FastCryptMethod.createSecretKey(key);
		decrypt = FastCryptMethod.createCipher(secretKey, Cipher.DECRYPT_MODE);
		encrypt = FastCryptMethod.createCipher(secretKey, Cipher.ENCRYPT_MODE);
	}

	public String encrypt(String text) throws Exception {
		return new String(Base64.encodeBase64(encrypt.doFinal(text.getBytes())));
	}

	public String decript(String text) throws Exception {
		return new String(decrypt.doFinal(Base64.decodeBase64(text)));
	}

}