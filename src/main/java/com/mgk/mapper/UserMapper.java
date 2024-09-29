package com.mgk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insertUser(@Param("snsToken") String snsToken, @Param("email") String email, @Param("nickname") String nickname, @Param("snsType") String snsType, @Param("favorList") String favorListArr);
}