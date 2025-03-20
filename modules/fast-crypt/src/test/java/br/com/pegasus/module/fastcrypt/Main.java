package br.com.pegasus.module.fastcrypt;

import br.com.pegasus.module.fastcrypt.service.FastCryptService;

public class Main {

	public static void main(String[] args) throws Exception {
		String chaveAcesso = "xxxxxxxxxx";
		FastCryptService crypt = new FastCryptService(chaveAcesso);

		String texto = "texto a ser criptografado";
		String sEncrypt = crypt.encrypt(texto);
		String sDecrypt = crypt.decript(sEncrypt);

		System.out.println("Dado encriptografado :: " + sEncrypt);
		System.out.println("Dado descriptografado :: " + sDecrypt);
	}

}
