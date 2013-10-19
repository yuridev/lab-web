package br.com.lab.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ClienteRepositoryImplTest {

    @Test public void fakeTest() {
  		assertNotNull("put something real.", new ClienteRepositoryImpl(null));
  	}
}

