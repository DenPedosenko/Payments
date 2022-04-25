package com.epam.data.model;

import java.util.Objects;

public class UserAccount {

	private int id;
	private String name;
	private double balance;
	private AccountStatus accountStatus;

	public UserAccount(int id, String name, AccountStatus accountStatus, double balance) {
		this.id = id;
		this.name = name;
		this.accountStatus = accountStatus;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountStatus, balance, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAccount other = (UserAccount) obj;
		return Objects.equals(accountStatus, other.accountStatus)
				&& Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& Objects.equals(name, other.name) ;
	}

	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", name=" + name + ", balance=" + balance
				+ ", accountStatus=" + accountStatus + "]";
	}

}
