package com.epam.data.model;

import java.util.Objects;

public class UserStatus {
	private int id;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserStatus other = (UserStatus) obj;
		return id == other.id && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "UserStatus [id=" + id + ", status=" + status + "]";
	}

}
