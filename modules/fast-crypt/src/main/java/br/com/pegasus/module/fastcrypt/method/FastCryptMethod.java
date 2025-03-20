package br.com.pegasus.module.fastcrypt.method;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import br.com.pegasus.module.fastcrypt.consts.FastCryptConsts;
import br.com.pegasus.module.fastcrypt.exception.CreateCipherFastCryptException;

public final class FastCryptMethod {

	public static final Key createSecretKey(String key) {
		// ajusta a chave para string de bytes em base 64
		key = new String(Base64.encodeBase64(key.getBytes()));
		return new SecretKeySpec(key.getBytes(), FastCryptConsts.ALGORITHM);
	}

	public static final Cipher createCipher(Key key, int cryptMode) {
		try {
			Cipher cipher = Cipher.getInstance(FastCryptConsts.ALGORITHM);
			cipher.init(cryptMode, key);
			return cipher;
		} catch (Exception ex) {
			throw new CreateCipherFastCryptException(ex);
		}
	}

}
