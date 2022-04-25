package com.epam.data.model;

import java.util.Objects;

public class UserType {
	private int id;
	private String name;

	public UserType(int id, String type) {
		this.id = id;
		this.name = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return name;
	}

	public void setType(String type) {
		this.name = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserType userType = (UserType) o;
		return id == userType.id && Objects.equals(name, userType.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "UserType{" + "id=" + id + ", type='" + name + '\'' + '}';
	}

}
