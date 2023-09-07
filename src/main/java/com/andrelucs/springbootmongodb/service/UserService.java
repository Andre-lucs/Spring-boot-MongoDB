package com.andrelucs.springbootmongodb.service;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
