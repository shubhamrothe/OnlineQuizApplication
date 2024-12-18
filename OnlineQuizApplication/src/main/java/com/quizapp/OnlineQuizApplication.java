package com.quizapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class OnlineQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineQuizApplication.class, args);
		
	}

	
	@Bean
    public CommandLineRunner generatePassword() {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = "Shubham123@"; // Your desired password
            String encodedPassword = encoder.encode(rawPassword);
            System.out.println("Encoded Password: " + encodedPassword);
        };
    }
}
