package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserTokenMapper {
    @Insert("Insert into UserToken values(#{id},#{token})")
    int SaveToken(UserToken userToken);
    @Select("Select id from UserToken where id=#{id} and token=#{token}")
    String CheckToken(UserToken userToken);
    @Select("select id from UserToken where id=#{id}")
    String checkAccount(UserToken userToken);
    @Update("Update UserToken  set token=#{token} where id=#{id}")
    int UpdateToken(UserToken userToken);
}
