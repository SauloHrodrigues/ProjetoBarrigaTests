package br.sp.srodrigues.rest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import br.sp.srodrigues.rest.core.BaseTest;

public class BarrigaTests extends BaseTest{

	@Test
	public void naoDeveAcessarAPOSemToken() {
		given()
		.when()
			.get("/contas")
		.then()
			.statusCode(401)
		;
	}

	@Test
	public void deveIncluirContaComSucesso() {
		Map<String, String> login = new HashMap();
		login.put("email", "saulohrodrigues@gmail.com");
		login.put("senha", "123456");
		
		String token =given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		;
		
		given()
			.header("Authorization", "JWT "+ token) // apis mais novas (JWT ou bearer)
			.body("{\"nome\":\"qualquer conta1\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)			
		;
	}
	
}
