package com.example.blog.controller;

import com.example.blog.entity.Users;
import com.example.blog.repository.UsersRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UsersRepository usersRepository;

    @PostMapping("/dummy/join")
    public ResponseEntity<Users> join(Users users) {
        System.out.println(users.toString());
        usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.OK).body(findUsersByUsername(users.getUsername()));
    }

    private Users findUsersByUsername(String username) {
        Optional<Users> res = usersRepository.findByUsername(username);
        if (res.isEmpty()) { throw new IllegalArgumentException(String.format("Users에서 %d의 username을 찾을 수 없습니다.", username)); }
        return res.get();
    }

}
