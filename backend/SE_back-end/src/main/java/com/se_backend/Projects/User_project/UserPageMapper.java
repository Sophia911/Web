package com.se_backend.Projects.User_project;

import com.se_backend.DB.DB_object.User;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface UserPageMapper {

    @Select("select id,name,avatar,background,introduction from User where id=#{id}")
    UserPage get_UserInform(User user);
}
