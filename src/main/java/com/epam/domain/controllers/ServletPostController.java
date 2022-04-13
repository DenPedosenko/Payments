package com.epam.domain.controllers;

import java.sql.Connection;
import java.util.Arrays;

public enum ServletPostController {
		LoginController("/login") {
			@Override
			public PostController getController(Connection connection) {
				return Login.getIntsance(connection);

			}
		},
		RegistrationController("/registration") {
			@Override
			public PostController getController(Connection connection) {
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

		public abstract PostController getController(Connection connection);

		public static ServletPostController findRoute(String route) throws IllegalArgumentException {
			return Arrays.stream(ServletPostController.values()).filter(v -> v.getRoute().equals(route)).findFirst()
					.orElseThrow(() -> new IllegalArgumentException(
							String.format("Unknown ServletController.route: '%s'", route)));
		}
	
}
