package com.qa.integration;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;

@Path("/account")
public class AccountEndpoint {
	
	@Inject
	private AccountDBRepository accountDBRepository;
	
	@GET
	@Path("/{id : \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id") @Min(1) String id) {
		
		Account account = accountDBRepository.find(id);
		
		if(account==null)
			return Response.status(Response.Status.NOT_FOUND).build();
		return Response.ok(account).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account account, @Context UriInfo uriInfo) {
		account = accountDBRepository.create(account);
		URI createdUri = uriInfo.getBaseUriBuilder().path(account.getId().toString()).build();
		return Response.created(createdUri).build();
	}
	
	@DELETE
	@Path("/{id : \\d+}")
	public Response deletetAccount(@PathParam("id") @Min(1) String id) {
		accountDBRepository.delete(id);
		return Response.noContent().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts(){
		List<Account> accounts = accountDBRepository.findAll();
		
		if (accounts.size() == 0)
			return Response.noContent().build();
		
		return Response.ok(accounts).build();
		
	}
	
	@GET
	@Path("/count")
	public Response countAccounts() {
		Long noOfAccounts = accountDBRepository.countAll();
		
		if (noOfAccounts == 0)
			return Response.noContent().build();
		return Response.ok(noOfAccounts).build();
	}
	
}


