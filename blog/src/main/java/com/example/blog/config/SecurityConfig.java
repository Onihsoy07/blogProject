package com.example.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근 시 권한 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable();
//        http.csrf().disable();
        //vm 추가(로그인 안함) -Dfile.encoding=UTF-8

        http
            .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .anyRequest()
                    .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm");

    }

}
