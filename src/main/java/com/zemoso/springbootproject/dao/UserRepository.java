package com.zemoso.springbootproject.dao;

import com.zemoso.springbootproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {

//    @Query("select id from User where User.userName= :userName")
//    public User findByuserName(String userName);
}
