package br.sp.srodrigues.rest.core;

import io.restassured.http.ContentType;

public interface Constatntes {
	String APP_BASE_URL = "http://barrigarest.wcaquino.me";
	Integer APP_PORT = 80; // se fosse https -> porta = 443
	String APP_BASE_PATH =""; // Isso seria usado em caso de deferentes versoes da API
	ContentType APP_CONTENT_TYPE = ContentType.JSON;
	Long MAX_TIMEOUT = 5000L;
}
