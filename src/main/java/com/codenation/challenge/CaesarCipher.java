package com.codenation.challenge;

abstract class CaesarCipher {
	public CaesarCipher() {
	}

	// decifrar texto
	public static String decipher(String cifrado, int numero_casas) {
		char newValue = ' ';
		String encryptedText = "";
		
		for (int i = 0; i < cifrado.length(); i++) {

			int originalCharCode = cifrado.codePointAt(i);
			int newCharCode = originalCharCode - numero_casas; // new character and code

			if (newCharCode >= 97 && newCharCode <= 122) { // just letters
				newValue = (char) (cifrado.codePointAt(i) - numero_casas);
				encryptedText += Character.toString(newValue);
			}

			else if (newCharCode < 97) {
				if (originalCharCode >= 97) {
					newValue = (char) ((cifrado.codePointAt(i) - numero_casas) + 26);
					encryptedText += Character.toString(newValue);
				} else {
					encryptedText += cifrado.charAt(i);
				}
			}
		}
		
		return encryptedText;
	}
}
