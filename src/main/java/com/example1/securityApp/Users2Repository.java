package com.example1.securityApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
                                                                  //-------------------------------USER
@Repository
public interface Users2Repository extends JpaRepository<Users ,Long> {

    @Query("SELECT a FROM Users a join a.roles r WHERE a.username = :username and r.roleName='USER'")
    public List<Users> findUser(@Param("username") String username);


}
