package com.techacademy.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    /** retrieve and return all */
    public List<User> getUserList() {
     // Call the findAll method of the repository
        return userRepository.findAll();
    }

    /** retrieve and return one User */
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }


    /** register user */
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    // ----- 追加:ここから -----
    /** delete user */
    @Transactional
    public void deleteUser(Set<Integer> idck) {
        for(Integer id : idck) {
            userRepository.deleteById(id);
        }
    }
    // ----- 追加:ここまで -----

}

