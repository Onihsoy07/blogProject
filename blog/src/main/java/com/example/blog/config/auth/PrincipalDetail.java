package com.example.blog.config.auth;

import com.example.blog.entity.Users;
import com.example.blog.service.impl.UsersServiceImpl;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class PrincipalDetail implements UserDetails {

    private Users users;

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getUsername();
    }
    //계정이 만료(expired)되지 않았는지 리턴 (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지 리턴 (true:안잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호(credential)가 만료(expired)되지 않았는지 리턴 (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화(사용가능)상태 리턴 (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정의 권한 목록 리턴 (여러개 가능)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> { return "ROLE_" + users.getRole(); });

        return collection;
    }

}
