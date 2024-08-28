package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.UserRole;
import com.se_backend.Projects.User.UserObject;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserRoleMapper {

    @Select("select UserID, RoleID from UserRole")
    ArrayList<UserRole> getUserRole_all();

    @Select("select DISTINCT UserID from UserRole")
    ArrayList<String> getAllUser();

    @Select("select RoleID from UserRole where UserID=#{userId}")
    ArrayList<String> getUserRole(@Param("userId")String userId);

    @Insert("Insert into UserRole values(#{id}, 3, null)")
    int addVisitorRole(UserObject userObject);

    @Delete("delete from UserRole where UserID = #{id}")
    int deleteUserRole(String id);

    @Insert("insert into UserRole values(#{email}, #{power}, null)")
    int addRole(@Param("email")String email, @Param("power")Integer power);

    @Delete("delete from UserRole where UserID=#{email} and RoleID=#{power}")
    int delRole(@Param("email")String email, @Param("power")Integer power);
}
