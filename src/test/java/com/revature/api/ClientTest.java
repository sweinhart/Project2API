package com.revature.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.revature.api.domain.Client;
public class ClientTest {
	@Test
	public void testClient(){
		Client client = new Client("Test", "000-000-0000", "test@test.com");
		System.out.println(client.toString());
		assertEquals("Test", client.getName());
		assertEquals("000-000-0000", client.getPhone());
		assertEquals("test@test.com", client.getEmail());
	}
}
