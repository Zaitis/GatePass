package com.sk.GatePass;

import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class GatePassApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(GatePassApplication.class);
		application.setAdditionalProfiles("production");
		application.run(args);
	}

}
