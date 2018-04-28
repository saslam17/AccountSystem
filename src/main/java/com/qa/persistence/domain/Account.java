package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account {

	@Id @GeneratedValue 
	@Size (min =1, max = 100)
	private String id;
	
	@Column(length = 10)
	@NotNull
	private String forename;
	
	@NotNull
	@Column(length = 10)
	private String surname;
	
	
	@Size (min = 1, max = 300)
	@Column(name = "account_number")
	private String accountNumber;
	
	public Account() {
		
	}

		public Account(String id, String forename, String surname, String accountNumber) {
			this.id = id;
			this.forename = forename;
			this.surname = surname;
			
			this.accountNumber = accountNumber;
		}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	

	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", forename=" + forename + ", surname=" + surname + 
				", accountNumber=" + accountNumber + "]";
	}

	
	
	
	
}