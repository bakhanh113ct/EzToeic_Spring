package com.alibou.security;

import com.alibou.security.auth.AuthenticationRequest;
import com.alibou.security.auth.AuthenticationService;
import com.alibou.security.auth.RegisterRequest;
import com.alibou.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static com.alibou.security.user.Role.ADMIN;
import static com.alibou.security.user.Role.USER;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
//			var admin = RegisterRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
//					.email("admin@mail.com")
//					.password("password")
//					.role(ADMIN)
//					.build();
//			System.out.println("Admin token: " + service.register(admin).getAccessToken());
//
//			var user = RegisterRequest.builder()
//					.firstname("User")
//					.lastname("User")
//					.email("user@mail.com")
//					.password("password")
//					.role(USER)
//					.build();
//			System.out.println("USER token: " + service.register(user).getAccessToken());

			var admin = AuthenticationRequest.builder()
//					.firstname("Admin")
//					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
//					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.authenticate(admin).getAccessToken());
//
			var user = AuthenticationRequest.builder()
//					.firstname("User")
//					.lastname("User")
					.email("user@mail.com")
					.password("password")
//					.role(USER)
					.build();
			System.out.println("USER token: " + service.authenticate(user).getAccessToken());


		};
	}
}
