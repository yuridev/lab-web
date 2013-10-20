package br.com.lab.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class OrcamentoControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new OrcamentoController(null, null, null, null, null, null, null, null));
 	}
}
