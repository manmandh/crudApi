package com.marry.crudapi.service;

import com.marry.crudapi.dto.request.UserCreationRequest;
import com.marry.crudapi.entity.User;
import com.marry.crudapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //CRUD operations
    public User createRequest(UserCreationRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
}
