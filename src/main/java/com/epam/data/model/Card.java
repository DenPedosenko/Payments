package com.epam.data.model;

import java.util.Objects;

public class Card {
	private int id;
	private String cardNumber;
	private String expDate;
	private String cvv;
	private UserAccount account;

	public Card(int id, String cardNumber, String expDate, String cvv, UserAccount account) {
		this.id = id;
		this.cardNumber = cardNumber;
		this.expDate = expDate;
		this.cvv = cvv;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public UserAccount getAccount() {
		return account;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, cardNumber, cvv, expDate, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(account, other.account) && Objects.equals(cardNumber, other.cardNumber)
				&& Objects.equals(cvv, other.cvv) && Objects.equals(expDate, other.expDate) && id == other.id;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", cardNumber=" + cardNumber + ", expDate=" + expDate + ", cvv=" + cvv + ", account="
				+ account + "]";
	}

}
