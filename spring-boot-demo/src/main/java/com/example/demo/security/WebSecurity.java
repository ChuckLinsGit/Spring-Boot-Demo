//package com.example.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurity extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers("/").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
//    }
//
//    /**
//     * security中需要为注册的用户和密码执行加密编码操作，因此需要PasswordEncoder将信息封装成{id}encodedPassword形式
//     * 否则将出现java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"的异常
//     * 出现这种异常简单但不安全的解决方案是，取消加密编码操作，加入一下代码即可
//     * 否则必须配置PasswordEncoder
//     * @return
//     */
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                    .withUser("user").password("password").roles("USER");
//    }
//}
