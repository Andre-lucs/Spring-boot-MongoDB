package com.andrelucs.springbootmongodb.dto;

import com.andrelucs.springbootmongodb.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorDTO {
    private String id;
    private String name;

    public AuthorDTO(){}

    public AuthorDTO(User obj){
        id = obj.getId();
        name = obj.getName();
    }
}
