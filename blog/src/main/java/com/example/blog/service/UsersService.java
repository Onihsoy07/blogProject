package com.example.blog.service;

import com.example.blog.entity.Users;

public interface UsersService {

    int join(Users users);

    void updateUser(Users users);

}
