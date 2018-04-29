package com.qa.repository;


import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.qa.business.repository.AccountDBRepository;
import com.qa.integration.AccountEndpoint;
import com.qa.integration.JAXRSconfiguration;
import com.qa.persistence.domain.Account;

@RunWith(Arquillian.class)
@RunAsClient
public class AccountEndpointTest {
	
	@Test
	public void createAccount(@ArquillianResteasyResource("api/accounts") WebTarget webTarget) throws Exception {
		
		//Test counting accounts
		Response response = webTarget.path("count").request().get();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
		
		// Test Find All
		response = webTarget.request(MediaType.APPLICATION_JSON).get();
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
		// creates an account
		
		Account account = new Account("id", "forename", "surname", "accountNumber");
		response = webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(account, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
	}
	
	
	@Deployment(testable = false)
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(AccountDBRepository.class)
				.addClass(Account.class)
				.addClass(AccountEndpoint.class)
				.addClass(JAXRSconfiguration.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/presistence.xml", "presistence.xml");

}
}