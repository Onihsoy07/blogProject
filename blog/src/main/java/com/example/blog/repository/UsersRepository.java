package com.example.blog.repository;

import com.example.blog.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Users findByUsernameAndPassword(String username, String password);

}
