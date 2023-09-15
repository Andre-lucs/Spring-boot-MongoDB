package com.andrelucs.springbootmongodb.resource;

import com.andrelucs.springbootmongodb.domain.Post;
import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.UserDTO;
import com.andrelucs.springbootmongodb.resource.util.URL;
import com.andrelucs.springbootmongodb.service.PostService;
import com.andrelucs.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        var response = postService.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Post> findById(@RequestParam("id") String id){
        Post response = postService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/findbytitle")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        String decodedText = URL.decodeParam(text);
        var response = postService.findByTitle(decodedText);
        return ResponseEntity.ok().body(response);
    }

}
