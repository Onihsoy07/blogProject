package com.example.blog.controller;

import com.example.blog.entity.Role;
import com.example.blog.entity.Users;
import com.example.blog.repository.UsersRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DummyControllerTest {

    private final UsersRepository usersRepository;

    @PostMapping("/dummy/join")
    public ResponseEntity<Users> join(Users users) {
        System.out.println(users.toString());
        users.setRole(Role.USER);
        usersRepository.save(users);
        return ResponseEntity.status(HttpStatus.OK).body(findUsersByUsername(users.getUsername()));
    }

    @GetMapping("/dummy/user/{id}")
    public ResponseEntity<Users> getUser(@PathVariable final Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(findUsersById(id));
    }

    @GetMapping("/dummy/users")
    public ResponseEntity<List<Users>> allUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(usersRepository.findAll());
    }

    @GetMapping("/dummy/user")
    public List<Users> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) {
        List<Users> users = usersRepository.findAll(pageable).getContent();
        return users;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public ResponseEntity<Users> updateUsers(@PathVariable final Long id, @RequestBody Users requestUser) {
        Users user = findUsersById(id);
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUsers(@PathVariable final Long id) {
        Users users = findUsersById(id);
        usersRepository.delete(users);

        return String.format("ID : %d 의 Users가 삭제되었습니다.", id);
    }

    private Users findUsersByUsername(String username) {
        Optional<Users> res = usersRepository.findByUsername(username);
        if (res.isEmpty()) { throw new IllegalArgumentException(String.format("Users에서 %d의 username을 찾을 수 없습니다.", username)); }
        return res.get();
    }

    private Users findUsersById(Long id) {
        Optional<Users> res = usersRepository.findById(id);
        if (res.isEmpty()) { throw new IllegalArgumentException(String.format("Users에서 %d의 id를 찾을 수 없습니다.", id)); }
        return res.get();
    }

}
