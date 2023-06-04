package com.example.kiemtra.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kiemtra.Entity.User;
import com.example.kiemtra.Repository.IRoleRepository;
import com.example.kiemtra.Repository.IUserRepository;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    // public User getUserByUser(String username, String password) {
    //     var user = userRepository.findByUser(username, password);
    //     return user;
    // }

    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("NguoiDung");
        if (roleId != 0 && userId != 0) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }
}
