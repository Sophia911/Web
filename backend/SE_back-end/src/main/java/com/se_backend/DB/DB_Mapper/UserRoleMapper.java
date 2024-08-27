package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface UserRoleMapper {

    @Select("select UserID, RoleID from UserRole")
    ArrayList<UserRole> getUserRole_all();

}
