package br.com.lab.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ParametroControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new ParametroController(null, null, null));
 	}
}
