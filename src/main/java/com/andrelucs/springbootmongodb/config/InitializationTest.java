package com.andrelucs.springbootmongodb.config;

import com.andrelucs.springbootmongodb.domain.Post;
import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.AuthorDTO;
import com.andrelucs.springbootmongodb.dto.CommentDTO;
import com.andrelucs.springbootmongodb.repository.PostRepository;
import com.andrelucs.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile(value = "test")
public class InitializationTest implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User u1 = new User("joao", "jj@gmail.com");
        User u2 = new User( "Maria Brown", "maria@gmail.com");
        User u3 = new User( "Alex Green", "alex@gmail.com");
        User u4 = new User("Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(u1,u2,u3,u4));

        Post p1 = new Post(new Date(System.currentTimeMillis()), "almoção", "o almoço hj foi bom pra caraca", u1);
        Post p2 = new Post(new Date(System.currentTimeMillis()), "Meu notebook chegou!!!", "finalmente meu notebook chegou, agora posso usar um notebook sem ter que esperar minutos pra poder abrir algo.", u1);
        CommentDTO c1 = new CommentDTO("Daora esse notebook cara", new Date(System.currentTimeMillis()),new AuthorDTO(u3));
        CommentDTO c2 = new CommentDTO("finalmente hein", new Date(System.currentTimeMillis()),new AuthorDTO(u2));
        p2.getComments().add(c1);
        p2.getComments().add(c2);

        postRepository.saveAll(Arrays.asList(p1,p2));

        u1.getPosts().add(p1);
        u1.getPosts().add(p2);
        userRepository.saveAll(Arrays.asList(u1,u2,u3, u4));


    }
}
