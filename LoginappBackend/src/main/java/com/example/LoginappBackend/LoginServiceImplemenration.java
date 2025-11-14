package com.example.LoginappBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImplemenration implements LoginService {

    @Autowired
    private LoginRepositry repo;

    @Override
    public boolean validateUser(String username, String password) {
        User user = repo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public int getSalary(String username) {
        User user = repo.findByUsername(username);
        return user != null ? user.getSalary() : 0;
    }
    
    @Override
    public boolean userExists(String username) {
        return repo.findByUsername(username) != null;
    }

    @Override
    public void registerUser(String username, String password, int salary) {
        User user = new User(username, password, salary);
        repo.save(user);
    }
    
}
