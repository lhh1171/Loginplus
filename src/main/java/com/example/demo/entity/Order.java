package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    String sid;
    long timestamp;
    String tel;
    int count;
    int sum;
}
