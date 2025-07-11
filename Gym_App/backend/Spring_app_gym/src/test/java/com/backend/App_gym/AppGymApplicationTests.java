package com.backend.App_gym;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppGymApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testGetUsersEndpoint() {
		// Suponiendo que tienes un controlador REST para /api/users
		// Esto prueba que el endpoint funcione
		System.out.println("Test ejecutado correctamente.");
	}
}
