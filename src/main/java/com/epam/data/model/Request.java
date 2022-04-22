package com.epam.data.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
	private int id;
	private User user;
	private UserAccount account;
	private LocalDateTime date;
	private RequestStatus status;
	
	public Request(int id, User user, UserAccount account, LocalDateTime date, RequestStatus status) {
		this.id = id;
		this.user = user;
		this.account = account;
		this.date = date;
		this.setStatus(status);
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public UserAccount getAccount() {
		return account;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAccount(UserAccount account) {
		this.account = account;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, date, id, status, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		return Objects.equals(account, other.account) && Objects.equals(date, other.date) && id == other.id
				&& Objects.equals(status, other.status) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", user=" + user + ", account=" + account + ", date=" + date + ", status=" + status
				+ "]";
	}
	
	
}
