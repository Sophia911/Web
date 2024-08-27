package com.se_backend.Projects.User;

import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface UserMapper {
    @Insert("Insert into User values(#{id},'User', #{password}, null, null, null,default)")
    int Insert(UserObject userObject);

    @Delete("Delete from User where id = #{id}")
    int Delete(UserObject userObject);

    @Select("Select id from User")
    ArrayList<UserObject> selectAll();

    @Select("Select password from User where id = #{id}")
    String getPasswordByEmail(@Param("id")String id);

    @Select("Select 1 from User where id = #{id} limit 1")
    String selectByEmail(@Param("id")String id);

    @Update("Update User set password = #{password} where id = #{id}")
    int Update(UserObject userObject);
}
