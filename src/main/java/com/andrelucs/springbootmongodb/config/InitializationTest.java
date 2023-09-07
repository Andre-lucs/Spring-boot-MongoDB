package com.andrelucs.springbootmongodb.config;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile(value = "test")
public class InitializationTest implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        User u1 = new User("joao", "jj@gmail.com");
        User u2 = new User( "Maria Brown", "maria@gmail.com");
        User u3 = new User( "Alex Green", "alex@gmail.com");
        User u4 = new User("Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));
    }
}
