package com.qa.integration;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;

@Path("/account")
public class AccountEndpoint {
	
	@Inject
	private AccountDBRepository accountDBRepository;
	
	
	public Account getAccount(String id) {
		return accountDBRepository.find(id);
	}
	
	
	public Account createAccount(Account account) {
		return accountDBRepository.create(account);
	}
	
	public void deleteAccount(String id) {
		accountDBRepository.delete(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks(){
		List<Account> accounts = accountDBRepository.findAll();
		
		if (accounts.size() == 0)
			return Response.noContent().build();
		
		return Response.ok(accounts).build();
		
	}
	
	
	public Long countAll() {
		return accountDBRepository.countAll();
	}
	
}


