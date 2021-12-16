package com.example1.securityApp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
                                                                           //------------------ADMIN
@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
/*

    @Query("SELECT a FROM Users a join a.roles r WHERE a.username = :username and r.roleName='USER'")
    public List<Users> findUser(@Param("username") String username);
*/

    @Query("SELECT u FROM Users u join u.roles r WHERE u.username = :username and r.roleName='ADMIN'")
    public List<Users> findAdmin(@Param("username") String username);


}