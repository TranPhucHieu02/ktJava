package com.example.kiemtra.Utils;

import com.example.kiemtra.Services.CustomUserDetailService;

import jakarta.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/assets/**", "/", "/register", "/error")
                        .permitAll()
                        .requestMatchers("/nhanvien/edit", "/nhanvien/delete")
                        .hasAnyAuthority("ADMIN")
                        .anyRequest().authenticated())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                            if (userDetails.getAuthorities().stream()
                                    .anyMatch(auth -> auth.getAuthority().equals("ADMIN"))) {
                                response.sendRedirect("/nhanvien");
                            } else {
                                response.sendRedirect("/login");
                            }
                        }))

                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                        .userDetailsService(userDetailsService()))
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage("/403"))
                .build();
    }
}

// package com.example.kiemtra.Utils;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import
// org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import com.example.kiemtra.Services.CustomUserDetailService;

// import jakarta.servlet.http.HttpSession;

// @Configuration
// @EnableWebSecurity
// @EnableMethodSecurity
// public class SecurityConfig {

// @Bean
// public UserDetailsService userDetailsService() {
// return new CustomUserDetailService();
// }

// @Bean
// public PasswordEncoder passwordEncoder() {
// return new BCryptPasswordEncoder();
// }

// @Bean
// public DaoAuthenticationProvider authenticationProvider() {
// DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
// auth.setUserDetailsService(userDetailsService());
// auth.setPasswordEncoder(passwordEncoder());
// return auth;
// }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// return http.csrf().disable()
// .authorizeHttpRequests(auth -> auth
// .requestMatchers("/css/**", "/js/**", "/assets/**", "/home/**", "/register",
// "/error")
// .permitAll()
// .requestMatchers("/admin/**")
// .hasAnyAuthority("QuanLy")
// .requestMatchers("/bacsi/**")
// .hasAnyAuthority("BacSi")
// .anyRequest().authenticated())
// .logout(logout -> logout
// .logoutUrl("/logout")
// .logoutSuccessUrl("/login")
// .deleteCookies("JSESSIONID")
// .invalidateHttpSession(true)
// .clearAuthentication(true)
// .permitAll())
// .formLogin(formLogin -> formLogin
// .loginPage("/login")
// .loginProcessingUrl("/login")
// // .usernameParameter("username") // Tên trường tên người dùng trên form
// // .passwordParameter("password") // Tên trường mật khẩu trên form
// .defaultSuccessUrl("/")
// .permitAll()
// .successHandler((request, response, authentication) -> {
// UserDetails userDetails = (UserDetails) authentication.getPrincipal();
// HttpSession session = request.getSession();
// session.setAttribute("infoUser", userDetails);
// if (userDetails.getAuthorities().stream()
// .anyMatch(auth -> auth.getAuthority().equals("QuanLy"))) {
// response.sendRedirect("/admin");
// } else if (userDetails.getAuthorities().stream()
// .anyMatch(auth -> auth.getAuthority().equals("BacSi"))) {
// response.sendRedirect("/bacsi");
// } else {
// response.sendRedirect("/");
// }
// }))
// .rememberMe(rememberMe -> rememberMe
// .key("uniqueAndSecret")
// .tokenValiditySeconds(86400)
// .userDetailsService(userDetailsService()))
// .exceptionHandling(exceptionHandling -> exceptionHandling
// .accessDeniedHandler((request, response, accessDeniedException) -> {
// // Xử lý khi truy cập không được phép
// UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
// .getAuthentication().getPrincipal();
// if (userDetails.getAuthorities().stream()
// .anyMatch(auth -> auth.getAuthority().equals("QuanLy"))) {
// response.sendRedirect("/admin");
// } else if (userDetails.getAuthorities().stream()
// .anyMatch(auth -> auth.getAuthority().equals("BacSi"))) {
// response.sendRedirect("/bacsi");
// } else {
// response.sendRedirect("/");
// }
// }))

// // .exceptionHandling(exceptionHandling ->
// // exceptionHandling.accessDeniedPage("/403"))
// // .exceptionHandling(exceptionHandling -> exceptionHandling
// // .authenticationEntryPoint((request, response, authException) -> {
// // // Xử lý khi truy cập yêu cầu xác thực, chuyển hướng đến trang lỗi 401
// // response.sendRedirect("/401");
// // }))
// .build();
// }
// }