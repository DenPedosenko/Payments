package com.epam.data.model;

import java.sql.Date;
import java.util.Objects;

public class Payment {
	private int id;
	private Date creationDate;
	private PaymentType paymentType;
	private PaymentStatus paymentStatus;
	private User user;
	private UserAccount userAccount;
	private double amount;

	public Payment(int id, Date creationDate, PaymentType paymentType, PaymentStatus paymentStatus, User user,
			UserAccount userAccount, double amount) {
		this.id = id;
		this.creationDate = creationDate;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.user = user;
		this.userAccount = userAccount;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", creationDate=" + creationDate + ", paymentType=" + paymentType
				+ ", paymentStatus=" + paymentStatus + ", user=" + user + ", userAccount=" + userAccount + ", amount="
				+ amount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, creationDate, id, paymentStatus, paymentType, user, userAccount);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payment other = (Payment) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(creationDate, other.creationDate) && id == other.id
				&& Objects.equals(paymentStatus, other.paymentStatus) && Objects.equals(paymentType, other.paymentType)
				&& Objects.equals(user, other.user) && Objects.equals(userAccount, other.userAccount);
	}

}
