package com.andrelucs.springbootmongodb.resource;

import com.andrelucs.springbootmongodb.domain.Post;
import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.UserDTO;
import com.andrelucs.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        var response = userService.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/")
    public ResponseEntity<UserDTO> findById(@RequestParam("id") String id){
        UserDTO response = new UserDTO(userService.findById(id));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/posts/")
    public ResponseEntity<List<Post>> getPosts(@RequestParam("id") String id){
        User u = userService.findById(id);
        List<Post> response = u.getPosts();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value ="/")
    public ResponseEntity<Void> insert(@RequestBody UserDTO obj){
		User user = userService.fromDTO(obj);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").queryParam("id", "{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<Void> deleteByID(@RequestParam("id") String id){
        userService.deleteById(id);
        return ResponseEntity.status(204).build();
    }

	@PutMapping(value = "/")
	public ResponseEntity<UserDTO> update(@RequestParam("id") String id, @RequestBody UserDTO obj){
        User user = userService.fromDTO(obj);
        user.setId(id);
		UserDTO response = new UserDTO(userService.update(user));
		return ResponseEntity.ok().body(response);
	}
}
