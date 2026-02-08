package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.smart.services.impl.SecurityCustomUserService;

@Configuration
public class SecurityConfig {

    @Autowired
  private SecurityCustomUserService customUserService;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider (){
      DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(customUserService);
      authenticationProvider.setPasswordEncoder(passwordEncoder());

      return authenticationProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
      
        httpSecurity.authorizeHttpRequests(authorise ->{
              authorise.requestMatchers("/user/**").authenticated();
              authorise.anyRequest().permitAll();
        });

        httpSecurity.csrf(csrf -> csrf.disable());

        httpSecurity.formLogin(formlogin ->{
          formlogin.loginPage("/login");
          formlogin.loginProcessingUrl("/authenticate");
          // Redirect after successful login (GET), avoids POST forward to dashboard
          formlogin.defaultSuccessUrl("/user/dashboard", true);
          formlogin.usernameParameter("email");
          formlogin.passwordParameter("password");
         
        });

        httpSecurity.logout(formlogout->{
          formlogout.logoutUrl("/logout");
        });
          
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder  passwordEncoder (){
        return new BCryptPasswordEncoder();
    }


}
