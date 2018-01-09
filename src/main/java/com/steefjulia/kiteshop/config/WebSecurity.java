/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author julia
 */
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;
  @Autowired
    private PasswordEncoder passwordEncoder;
  
  @Autowired
    private void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       
       auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select username, password, enabled from account where username=?")
               .authoritiesByUsernameQuery("select username, role from account where username=?") 
               .passwordEncoder(new BCryptPasswordEncoder());
    }
  
      @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                    .authorizeRequests()
                     .antMatchers("/", "/home", "/home/index", "/products/*", "/bestelling/*", "/login/**", "/klanten/*").permitAll()
                .antMatchers("/admin/*").access( "hasRole('ADMIN')")
                     .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll();
                
                
                
    }
  
  @Bean
  public PasswordEncoder passwordEncoder(){
    PasswordEncoder encoder = new BCryptPasswordEncoder();
    return encoder;
  }
  
}

 