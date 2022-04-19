package com.epam.domain.controllers;

import java.sql.Connection;
import java.util.Arrays;

public enum ServletPostController {
	MainController("/") {

		@Override
		public PostController getControllerInstance(Connection connection) {
			return Main.getInstance(connection);

		}

	},
	LoginController("/login") {
		@Override
		public PostController getControllerInstance(Connection connection) {
			return Login.getIntsance(connection);

		}
	},
	PaymentsController("/payments") {
		@Override
		public PostController getControllerInstance(Connection connection) {
			return Payments.getInstance(connection);
		}
	},
	RegistrationController("/registration") {
		@Override
		public PostController getControllerInstance(Connection connection) {
			return Registration.getInstance(connection);

		}
	};

	private String route;

	public String getRoute() {
		return route;
	}

	private ServletPostController(String route) {
		this.route = route;
	}

	public abstract PostController getControllerInstance(Connection connection);

	public static ServletPostController findRoute(String route) throws IllegalArgumentException {
		return Arrays.stream(ServletPostController.values()).filter(v -> v.getRoute().equals(route)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						String.format("Unknown ServletController.route: '%s'", route)));
	}

}
