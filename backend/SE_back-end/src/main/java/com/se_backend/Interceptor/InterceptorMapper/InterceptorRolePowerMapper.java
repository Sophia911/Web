package com.se_backend.Interceptor.InterceptorMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface InterceptorRolePowerMapper {
    @Select("Select PowerID from RolePower where RoleID=#{RoleID}")
    ArrayList<Integer>GetPowerID(@Param("RoleID")int RoleID);
}
