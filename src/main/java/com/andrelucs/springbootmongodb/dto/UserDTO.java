package com.andrelucs.springbootmongodb.dto;

import com.andrelucs.springbootmongodb.domain.User;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private String id;
    private String name;
    private String email;

    public UserDTO(){}

    public UserDTO(User obj){
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }
}
