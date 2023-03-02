package com.new_ton.config.security;

import com.new_ton.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/productPage", "/calibrationPage", "/dischargePage", "/recipePage", "/service/export/excel/**", "/api/v1/**", "/role-action").access("hasAnyRole('ROLE_ACCOUNTMANAGER', 'ROLE_TECHNOLOGIST', 'ROLE_TESTER', 'ROLE_SUPERVISOR')");
        http.authorizeRequests().antMatchers("/**", "/login", "/logout").permitAll();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/role-action").failureUrl("/login?error=true").usernameParameter("login").passwordParameter("password");
    }

}
