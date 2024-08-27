package com.se_backend.Interceptor.InterceptorMapper;

import com.se_backend.DB.DB_object.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface InterceptorUerRoleMapper {
    @Select("Select * from UserRole where UserID=#{UserID}")
    ArrayList<UserRole> getUserAllRole(@Param("UserID")String UsrID);
}
