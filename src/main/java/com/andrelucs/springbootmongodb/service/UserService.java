package com.andrelucs.springbootmongodb.service;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.repository.UserRepository;
import com.andrelucs.springbootmongodb.service.exception.IncompleteDataException;
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

    public User insert(User obj){
        if(obj.haveNullAtb()) throw new IncompleteDataException("User is missing fields");
        User u = userRepository.save(obj);
        return u;
    }

    public boolean deleteById(String id){
        if(!userRepository.existsById(id)) return false;
        
        userRepository.deleteById(id);
        return true;
    }
}
