package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public enum Role {
    ADMIN("admin"), MANAGER("manager"), USER("user");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
