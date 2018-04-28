package com.qa.business.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.qa.persistence.domain.Account;

@Transactional(Transactional.TxType.SUPPORTS)
public class AccountDBRepository {
	
	@PersistenceContext(unitName = "accountSystemPU")
	private EntityManager manager;
	
	
	public Account find(@NotNull String accountId) {
		return manager.find(Account.class, accountId);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public Account create(@NotNull Account account) {
		manager.persist(account);
		return account;
	}
	@Transactional(Transactional.TxType.REQUIRED)
	public void delete(@NotNull String id) {
		manager.remove(manager.getReference(Account.class, id));
	}
	
	
	public List<Account> findAll(){
		
		TypedQuery<Account> query = manager.createQuery("SELECT a FROM Account a order by a.accountNumber", Account.class);
		return query.getResultList();
	}
	
	
	public Long countAll() {
		TypedQuery<Long> query = manager.createQuery("SELECT count(a) from Account a", Long.class);
		return query.getSingleResult();
	}
	
}
