package com.example.blog.config.auth;

import com.example.blog.entity.Users;
import com.example.blog.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    //스프링이 password 처리, 여기서 username이 DB에 있는지 확인
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users principal = usersRepository.findByUsername(username)
            .orElseThrow(() -> {
                throw new UsernameNotFoundException(String.format("해당 아이디 : %s 를 찾을 수 없습니다.",  username));
            });
        PrincipalDetail principalDetail = new PrincipalDetail(principal);
        System.out.println(principalDetail.getUsers().toString());
        return principalDetail;  //시큐리티 세션에 정보 저장됨
    }
}
