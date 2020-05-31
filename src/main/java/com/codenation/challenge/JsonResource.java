package com.codenation.challenge;

import java.io.File;

import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
abstract public class JsonResource {
	public static RestTemplate restTemplate = new RestTemplate();

	// metodo get com rest template
	public static JsonCodenation getJson(String urlApi, String token) {
		JsonCodenation json = restTemplate.getForObject(urlApi + token, JsonCodenation.class);

		return json;
	}

	// metodo post - file - multipart/form-data
	public static ResponseEntity<String> postJson(String urlPost, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("answer", new FileSystemResource("answer.json"));

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		String serverUrl = urlPost + token;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);

		return response;
	}

	/*
	  public static void uploadFile() { LinkedMultiValueMap<String, Object> map =
	  new LinkedMultiValueMap<>(); FileSystemResource value = new
	  FileSystemResource(new File("answer.json")); map.add("answer", value);
	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	  HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new
	  HttpEntity<>(map, headers); RestTemplate restTemplate = new RestTemplate();
	  restTemplate.exchange(
	  "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=f9d956070dba183eea0eef4eabbee77f6db80d5f",
	  HttpMethod.POST, requestEntity, String.class);	  
	  }
	  
	 */

}
