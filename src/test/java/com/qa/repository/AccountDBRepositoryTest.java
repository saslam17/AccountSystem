package com.qa.repository;

import static org.junit.Assert.*;

import java.awt.print.Book;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.qa.business.repository.AccountDBRepository;
import com.qa.persistence.domain.Account;

@RunWith(Arquillian.class)
public class AccountDBRepositoryTest {
	
	private static Long accountId;
	
	@Inject
	private AccountDBRepository accountDBRepository;
	//Test counting Accounts
	
	
	@Test(expected = Exception.class)
	public void findWithInvalidId(){
		accountDBRepository.find(null);
	}
	
	
	
	
	@Test(expected = Exception.class)
	public void createInvaliAccount() {
		Account account = new Account("id", "forename", "surname", "position", "accountNumber");
		account = accountDBRepository.create(account);
	}
	
	
	@Test
	public void create() throws Exception {
		
		assertEquals(Long.valueOf(0), accountDBRepository.countAll());
		assertEquals(0, accountDBRepository.findAll().size());
		
		//Create an Account
		Account account = new Account("id", "forename", "surname", "position", "accountNumber");
		account = accountDBRepository.create(account);
		String accountId = account.getId();
		
		//Check created Account
		assertNotNull(accountId);
		
		//Find created account
		Account accountFound = accountDBRepository.find(accountId);
		
		//Check the found Account
		assertEquals("id", accountFound.getId());
		
		//testing counting accounts
		
		assertEquals(Long.valueOf(1), accountDBRepository.countAll());
		assertEquals(1, accountDBRepository.findAll().size());
		
		//Delete the account
		accountDBRepository.delete(accountId);
		
		assertEquals(Long.valueOf(0), accountDBRepository.countAll());
		assertEquals(0, accountDBRepository.findAll().size());
		
		
		
	}
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClass(AccountDBRepository.class)
				.addClass(Account.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsManifestResource("META-INF/presistence.xml", "presistence.xml");
		
	}
	
	
	}




