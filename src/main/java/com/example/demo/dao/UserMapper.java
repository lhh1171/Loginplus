package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {


    @Select("SELECT password FROM user WHERE tel =#{tel}")
    String findPasswordByTel(@Param("tel") String tel);


    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "tel", column = "tel")
    })
    @Select("SELECT name,password,tel FROM user")
    List<User> findAll();

    @Insert("INSERT INTO user(name,password,tel) VALUES( #{name},#{password},#{tel})")
    void insert(@Param("name") String name,@Param("password") String password,@Param("tel") String tel);

    @Delete("DELETE FROM user WHERE uid =#{uid}")
    int delete(String uid);

    User findByToken(String token);
}
