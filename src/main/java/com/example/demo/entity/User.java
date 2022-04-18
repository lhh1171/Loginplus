package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lqc
 */

@Data
@AllArgsConstructor
public class User {
    String name;
    String password;
    String tel;
}
