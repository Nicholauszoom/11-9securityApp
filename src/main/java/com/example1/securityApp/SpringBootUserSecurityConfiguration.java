package com.example1.securityApp;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//@Configuration
//@Order(1)
//@EnableWebSecurity
//public class SpringBootUserSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    UserDetailsService userDetailsServiceImpl;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
////                .antMatcher("/login")
//                .authorizeRequests()
//                .antMatchers("/dashboard").authenticated()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/dashboard", true)
//                .permitAll()
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//
//        http.csrf().disable();
//    }
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(2)@EnableWebSecurity
public class SpringBootUserSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsService userDetailsServiceImpl;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);

        // use this code for inmemory database authentication
        //auth.inMemoryAuthentication()
        //	.withUser("user").password("{noop}password").roles("USER");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/login**")
                .authorizeRequests().antMatchers("/dashboard").authenticated().anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .failureUrl("/login?usererror=true")
                .permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        http.csrf().disable();
    }
}