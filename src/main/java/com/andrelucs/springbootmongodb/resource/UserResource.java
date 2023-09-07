package com.andrelucs.springbootmongodb.resource;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.UserDTO;
import com.andrelucs.springbootmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
