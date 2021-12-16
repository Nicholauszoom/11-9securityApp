package com.example1.securityApp;
//
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
//@Order(2)
//@EnableWebSecurity
//public class SpringBootAdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    UserDetailsService adminDetailsServiceImpl;
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//               // .antMatcher("/admin/**")
//                //.antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/admin/dashboard").authenticated()
//                .antMatchers("/register_course").authenticated()
//                .anyRequest().permitAll()
//                .and().formLogin()
//                .loginPage("/admin/login")
//                .defaultSuccessUrl("/admin/dashboard", true)
//                .permitAll()
//                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
//                //.and().exceptionHandling().accessDeniedPage("/admin/accessioned");
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
@Order(1)@EnableWebSecurity
public class SpringBootAdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsService adminDetailsServiceImpl;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder);

        // Use this code for inmemeory database authentication
        //auth.inMemoryAuthentication()
        //	.withUser("admin").password("{noop}password").roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/admin/**")
                .authorizeRequests().antMatchers("/admin/dashboard").authenticated().anyRequest().authenticated()
                .and().formLogin().loginPage("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
               // .failureUrl("/admin/login?adminerror=true")
                .permitAll()
                .and().logout().logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login");
        http.csrf().disable();

    }
}
