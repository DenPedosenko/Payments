package com.epam.domain.controllers;

import java.sql.Connection;
import java.lang.IllegalArgumentException;
import java.util.Arrays;

public enum ServletGetController {
	MainController("/") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Main.getInstance(connection);

		}
	},
	LoginController("/login") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Login.getIntsance(connection);

		}
	},
	RegistrationController("/registration") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Registration.getInstance(connection);

		}
	},
	PaymentsController("/payments") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Payments.getInstance(connection);
		}

	},
	OperationsController("/operations") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Operations.getInstance(connection);
		}

	},
	AccountsController("/accounts") {
		@Override
		public GetController getControllerInstance(Connection connection) {
			return Accounts.getInstance(connection);
		}
		
	},
	LogoutController("/logout") {
		@Override
		public GetController getControllerInstance(Connection connection) {
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

	public abstract GetController getControllerInstance(Connection connection);

	public static ServletGetController findRoute(String route) throws IllegalArgumentException {
		return Arrays.stream(ServletGetController.values()).filter(v -> v.getRoute().equals(route)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException(
						String.format("Unknown ServletController.route: '%s'", route)));
	}
}
