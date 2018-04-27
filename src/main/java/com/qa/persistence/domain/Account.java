package com.qa.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {

	@Id @GeneratedValue 
	private String id;
	
	@Column(length = 10)
	private String forename;

	@Column(length = 10)
	private String surname;
	
	@Column(length = 10)
	private String position;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	public Account() {
		
	}

		public Account(String id, String forename, String surname, String position, String accountNumber) {
			this.id = id;
			this.forename = forename;
			this.surname = surname;
			this.position = position;
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
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", forename=" + forename + ", surname=" + surname + ", position=" + position
				+ ", accountNumber=" + accountNumber + "]";
	}

	
	
	
	
}