package com.example.blog.config;

import com.example.blog.config.auth.PrincipalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소 접근 시 권한 미리 체크
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;

    @Bean
    public BCryptPasswordEncoder encoderPWD() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable();
//        http.csrf().disable();
        //vm 추가(로그인 안함) -Dfile.encoding=UTF-8

        http
            .csrf().disable()  //csfr(query로 공격) 토큰 비활성화 (테스트시 비활성화)   //xss(script 공격)  --> 추후 확인 필요
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/")
                .failureUrl("/auth/loginFail");
    }

    //여기서 시큐리티 로그인 정보 받음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encoderPWD());
    }

}
