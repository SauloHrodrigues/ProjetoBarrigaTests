package br.sp.srodrigues.rest.tests;

import static io.restassured.RestAssured.given;

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
}
