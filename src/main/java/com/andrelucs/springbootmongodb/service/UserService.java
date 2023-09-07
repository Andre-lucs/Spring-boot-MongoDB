package com.andrelucs.springbootmongodb.service;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.repository.UserRepository;
import com.andrelucs.springbootmongodb.service.exception.ObjectNotFoundException;
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

    public User findById(String id){
        User u = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));

        return u;
    }
}
