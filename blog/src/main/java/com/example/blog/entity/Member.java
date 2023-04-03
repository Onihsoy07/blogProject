package com.example.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member", schema = "blog", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private long id;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(name = "email", nullable = false, unique = false)
    private String email;

}
