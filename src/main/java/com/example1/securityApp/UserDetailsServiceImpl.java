package com.example1.securityApp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
 class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
     private final Users2Repository users2Repository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    UserDetailsServiceImpl(Users2Repository users2Repository) {
        this.users2Repository = users2Repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<Users> usersList = users2Repository.findUser(userName);
        if (usersList != null && usersList.size() == 1) {
            Users users = usersList.get(0);

            List<String> roleList = new ArrayList<>();
            for (Role role : users.getRoles()) {
                roleList.add(role.getRoleName());
            }
            return User.builder()
                    .username(users.getUsername())
                    //change here to store encoded password in db
                    .password( bCryptPasswordEncoder.encode(users.getPassword()) )
                    .disabled(users.isDisabled())
                    .accountExpired(users.isAccountExpired())
                    .accountLocked(users.isAccountLocked())
                    .credentialsExpired(users.isCredentialsExpired())
                    .roles(roleList.toArray(new String[0]))
                    .build();
        } else {
            throw new UsernameNotFoundException("User Name is not Found");
        }
    }
}
