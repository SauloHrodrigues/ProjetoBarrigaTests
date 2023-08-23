package br.sp.srodrigues.rest.tests;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import br.sp.srodrigues.rest.core.BaseTest;

public class BarrigaTests extends BaseTest{

	private String TOKEN;
	
	@Before
	public void login() {
		Map<String, String> login = new HashMap();
		login.put("email", "saulohrodrigues@gmail.com");
		login.put("senha", "123456");
		
		TOKEN =given()
			.body(login)
		.when()
			.post("/signin")
		.then()
			.statusCode(200)
			.extract().path("token");
		;
	}
	
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
		given()
			.header("Authorization", "JWT "+ TOKEN) // apis mais novas (JWT ou bearer)
			.body("{\"nome\":\"qualquer conta\"}")
		.when()
			.post("/contas")
		.then()
			.statusCode(201)			
		;
	}

	@Test
	public void deveAlterarContaComSucesso() {
		given()
			.header("Authorization", "JWT "+ TOKEN) // apis mais novas (JWT ou bearer)
			.body("{\"nome\":\"penelop charmosa - alterada\"}")
		.when()
			.put("/contas/1875562")// ID diferente a 
		.then()
			.statusCode(200)			
		;
	}
	@Test
	public void naoDeveInserirContaMesmoNome() {
		given()
			.header("Authorization", "JWT "+ TOKEN) // apis mais novas (JWT ou bearer)
			.body("{\"nome\":\"Conta Teste\"}")
		.when()
			.post("/contas/")
		.then()
			.log().all()
			.statusCode(400)
			.body("error",Matchers.is("Já existe uma conta com esse nome!"))
		;
	}
	
}
