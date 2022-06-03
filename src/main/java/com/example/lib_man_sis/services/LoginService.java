package com.example.lib_man_sis.services;

import com.example.lib_man_sis.models.User;
import com.example.lib_man_sis.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
    @Autowired
    private UserRepository repo;

    public User login(long libraryId, String password) {
        User user = repo.findByLibraryIdAndPassword(libraryId,password);
        return user;
    }


}
