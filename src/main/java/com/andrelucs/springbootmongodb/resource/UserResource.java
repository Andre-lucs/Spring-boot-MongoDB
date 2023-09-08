package com.andrelucs.springbootmongodb.resource;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.UserDTO;
import com.andrelucs.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

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

    @PostMapping(value ="/")
    public ResponseEntity<UserDTO> insert(@RequestBody User obj){
        UserDTO response = new UserDTO(userService.insert(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/?id={id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping(value = "/")
    public ResponseEntity<String> deleteByID(@RequestParam("id") String id){
        boolean deleted = userService.deleteById(id);
        return ResponseEntity.status((deleted) ? 204 : 404).body((deleted) ? "Deleted" : "Not Found");
    }
}
