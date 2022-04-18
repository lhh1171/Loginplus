package com.example.demo.service;



import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public int deleteUser(String uid) {
        return userMapper.delete(uid);
    }

    public void insertUser(User user) {
        userMapper.insert(user.getName(),user.getPassword(),user.getTel());
    }

    public List<User> findAll(){
        return  userMapper.findAll();
    }

    public String findPasswordByTel(String tel){
        return userMapper.findPasswordByTel(tel);
    }
}
