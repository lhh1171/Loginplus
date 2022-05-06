package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO MyOrder(sid,timestamp,tel,count,sum) VALUES(#{sid},#{timestamp},#{tel},#{count},#{sum})")
    void insert(@Param("sid") String sid, @Param("timestamp") long timestamp,
                @Param("tel") String tel, @Param("count") int count, @Param("sum") int sum);
}
