package com.andrelucs.springbootmongodb.service;

import com.andrelucs.springbootmongodb.domain.User;
import com.andrelucs.springbootmongodb.dto.UserDTO;
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
        User u = userRepository.insert(obj);
        return u;
    }

    public void deleteById(String id){
		findById(id);
        
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User u = findById(obj.getId());
		updateData(u, obj);
        return userRepository.save(u);
    }

	private void updateData(User u1, User u2){
		if(u2.getName() != null && u2.getName() != u1.getName()) u1.setName(u2.getName());
		if(u2.getEmail() != null && u2.getEmail() != u1.getEmail()) u1.setEmail(u2.getEmail());
	}

	public User fromDTO(UserDTO obj){
		return new User(obj.getId(), obj.getName(), obj.getEmail());
	}
}
