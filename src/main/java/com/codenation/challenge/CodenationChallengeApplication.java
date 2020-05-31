package com.codenation.challenge;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CodenationChallengeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CodenationChallengeApplication.class, args);

		String urlApi = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=";
		String token = "00000000"; //token único do usuário

		// get json
		JsonCodenation jsonOriginal = JsonResource.getJson(urlApi, token);

		// convert object to json
		JSONObject codenationJson = new JSONObject();
		codenationJson = JsonService.objectToJson(jsonOriginal.numero_casas, jsonOriginal.token, jsonOriginal.cifrado,
				jsonOriginal.decifrado, jsonOriginal.resumo_criptografico);

		// gerar arquivo com json
		JsonService.jsonToFile(codenationJson);

		String cifrado = jsonOriginal.cifrado;
		int numero_casas = 5;
		String decifrado = CaesarCipher.decipher(cifrado, numero_casas);

		// convert object to json
		codenationJson = JsonService.objectToJson(jsonOriginal.numero_casas, jsonOriginal.token, jsonOriginal.cifrado,
				decifrado, jsonOriginal.resumo_criptografico);

		// gerar arquivo com json
		JsonService.jsonToFile(codenationJson);

		// hash do texto decifrado
		String resumo_criptografico = Encrypt.sha1(decifrado).toLowerCase();

		// convert object to json
		codenationJson = JsonService.objectToJson(jsonOriginal.numero_casas, jsonOriginal.token, jsonOriginal.cifrado,
				decifrado, resumo_criptografico);

		// gerar arquivo com json
		JsonService.jsonToFile(codenationJson);

		//post josn file
		String urlPost = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=";
		JsonResource.postJson(urlPost, token);

		
	}

}
