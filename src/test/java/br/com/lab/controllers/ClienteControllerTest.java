package br.com.lab.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ClienteControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new ClienteController(null, null, null));
 	}
}
