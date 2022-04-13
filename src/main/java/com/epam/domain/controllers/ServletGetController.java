package com.epam.domain.controllers;

import java.sql.Connection;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

public enum ServletGetController {
	MainController("/") {

		@Override
		public GetController getController(Connection connection) {
			return Main.getInstance(connection);

		}

	},
	LoginController("/login") {
		@Override
		public GetController getController(Connection connection) {
			return Login.getIntsance(connection);

		}
	},
	RegistrationController("/registration") {
		@Override
		public GetController getController(Connection connection) {
			return Registration.getInstance(connection);

		}
	},
	LogoutController("/logout") {
		@Override
		public GetController getController(Connection connection) {
			return new Logout();

		}
	};

	private String route;

	public String getRoute() {
		return route;
	}

	private ServletGetController(String route) {
		this.route = route;
	}

	public abstract GetController getController(Connection connection);

	public static ServletGetController findRoute(String route) throws IllegalArgumentException {
		return Arrays.stream(ServletGetController.values()).filter(v -> v.getRoute().equals(route)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						String.format("Unknown ServletController.route: '%s'", route)));
	}
}
