package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.BackstageUser;
import com.se_backend.Projects.Backstage.Object.BackstageUserInfo.BackstageUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BackstageUserMapper {
    @Select("select * from User where id=#{id}")
    BackstageUser getUserInfo(String id);
}
