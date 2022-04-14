package com.epam.data.model;

import java.util.Objects;

public class UserStatus {
	private int id;
	private String name;

	public UserStatus(int id, String status) {
		this.id = id;
		this.name = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return name;
	}

	public void setStatus(String status) {
		this.name = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserStatus that = (UserStatus) o;
		return id == that.id && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "UserStatus{" + "id=" + id + ", status='" + name + '\'' + '}';
	}
}
