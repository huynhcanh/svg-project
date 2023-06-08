package com.example.svg_project.config;

import com.example.svg_project.security.jwt.AuthenticationEntryPointHandler;
import com.example.svg_project.security.jwt.AuthenticationFilter;
import com.example.svg_project.security.userpincal.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthenticationEntryPointHandler unauthorizedHandler;

    @Bean
    public AuthenticationFilter authenticationJwtTokenFilter() {
        return new AuthenticationFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // kiểu mã hóa BCrypt (bây giờ ít sài mã hóa md5)
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Cấu hình REST API
                .authorizeRequests()
                    // api auth
                    .antMatchers("/api/auth/**", "/api/generate-qr**").permitAll()
                    // template
                    .antMatchers("/css/**","/demo/**","/fonts/**","/img/**",
                            "/js/**", "/mylib/**", "/vendors/**").permitAll()
                    // controller
                    .antMatchers("/login**", "/classifications",
                        "/history", "/items", "/locations", "/moves", "/scan-qr").permitAll()
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf()
                    .disable()
                // Các url còn lại phải auth
                .authorizeRequests()
                    .anyRequest().authenticated();
    }
}