package com.boristenelsen.baureport;

import com.boristenelsen.baureport.model.User;
import com.boristenelsen.baureport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class BaureportApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(BaureportApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user = new User(new UUID(0,20),"test@test.de", "$2a$10$bermF4Ha3RW6do6sD4X9xO6kTS6BzK/LlsyI7c7kdjE4SF7sQRbMy","Mönchengladbach 41199", "Teststrasse 40","USER");
		if(userRepository.findUserByEmail(user.getEmail())==null) userRepository.save(user);
	}
}
