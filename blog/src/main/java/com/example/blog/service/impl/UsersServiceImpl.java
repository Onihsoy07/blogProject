package com.example.blog.service.impl;

import com.example.blog.controller.api.UserApiController;
import com.example.blog.dto.UsersDto;
import com.example.blog.entity.Users;
import com.example.blog.mapping.UsersMapping;
import com.example.blog.repository.UsersRepository;
import com.example.blog.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder encoder;

    private final AuthenticationManager authenticationManager;

    private final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Override
    @Transactional()
    public int join(Users user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);
        try {
            usersRepository.save(user);
            return 1;
        } catch (Exception e) {
//            e.printStackTrace();
            LOGGER.error("error : " + e.getMessage());
            LOGGER.info("회원가입에 실패하였습니다. error : " + e.getMessage());
        }
        LOGGER.info("service join -1 리턴");
        return -1;
    }

    @Override
    @Transactional
    public UsersDto updateUser(Users users) {
        Users updateUser = usersRepository.findById(users.getId())
                                            .orElseThrow(() -> {
                                                throw new IllegalArgumentException(String.format("User ID : %d를 찾을 수 없습니다.", users.getId()));
                                            });
        String encPassword = encoder.encode(users.getPassword());
        updateUser.setPassword(encPassword);
        updateUser.setEmail(users.getEmail());
        usersRepository.save(updateUser);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(updateUser.getUsername(), users.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LOGGER.info("SecurityContex authentication 변경 완료");

        return UsersMapping.UsersConvertToDto(updateUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
        LOGGER.info("User ID:{} 삭제 완료", id);
    }


}
