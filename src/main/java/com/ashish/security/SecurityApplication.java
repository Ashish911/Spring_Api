package com.ashish.security;

import com.ashish.security.auth.AuthenticationService;
import com.ashish.security.auth.RegisterRequest;
import com.ashish.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService authenticationService
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstName("Admin")
					.lastName("Admin")
					.email("admin@admin.com")
					.password("admin")
					.role(Role.ADMIN)
					.build();
			System.out.println("Admin Token: " + authenticationService.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstName("Manager")
					.lastName("Manager")
					.email("manager@manager.com")
					.password("manager")
					.role(Role.MANAGER)
					.build();
			System.out.println("Manager token: " + authenticationService.register(manager).getAccessToken());
		};
	}
}
