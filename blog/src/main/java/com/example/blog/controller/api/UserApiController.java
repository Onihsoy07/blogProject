package com.example.blog.controller.api;

import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Role;
import com.example.blog.entity.Users;
import com.example.blog.service.UsersService;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserApiController.class);

    private final UsersService usersService;

    @PostMapping("/auth/joinProc")
    public ResponseEntity<Integer> save(@RequestBody @Valid final Users users) {
        LOGGER.info("[post] /api/user save 호출됨");
        users.setRole(Role.USER);
        int result = usersService.join(users);
        if (result == 1) {
//            LOGGER.info("회원가입 완료 : user {}", users.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            LOGGER.info("[post] /api/user save error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<Integer> updateUser(@RequestBody final Users users) {
        LOGGER.info("[put] updateUser 호출됨.");
        usersService.updateUser(users);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }


}
