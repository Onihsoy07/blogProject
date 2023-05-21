package com.example.blog.mapping;

import com.example.blog.dto.UsersDto;
import com.example.blog.entity.Users;

public class UsersMapping {

    public static UsersDto UsersConvertToDto(Users users) {
        UsersDto dto = new UsersDto().builder()
            .id(users.getId())
            .username(users.getUsername())
            .password(users.getPassword())
            .email(users.getEmail())
            .role(users.getRole())
            .build();

        return dto;
    }

    public static Users UsersConvertToModel(UsersDto usersDto) {
        Users model = new Users().builder()
            .id(usersDto.getId())
            .username(usersDto.getUsername())
            .password(usersDto.getPassword())
            .email(usersDto.getEmail())
            .role(usersDto.getRole())
            .build();

        return model;
    }

}
