package com.codenation.challenge;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

abstract class JsonService {

	private static JSONObject codenationJson = new JSONObject();
	
	public JsonService() {
	}
	
	// converter objeto para json
	@SuppressWarnings("unchecked")//remove warning about "codenationJson.put"
	public static JSONObject objectToJson(int numberPosition, String token, String encrypted, String deciphered, String hashCode) {	
		codenationJson.put("numero_casas", numberPosition);
		codenationJson.put("token", token);
		codenationJson.put("cifrado", encrypted);
		codenationJson.put("decifrado", deciphered);
		codenationJson.put("resumo_criptografico", hashCode);
		return codenationJson;
	}
	
	// gerar arquivo com json
	public static void jsonToFile(JSONObject codenationJson) {
		try (FileWriter file = new FileWriter("answer.json")) {
			file.write(codenationJson.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
