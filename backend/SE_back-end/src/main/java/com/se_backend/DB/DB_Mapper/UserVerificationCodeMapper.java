package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.UserVerificationCode;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserVerificationCodeMapper {
    @Select("Select * from UserVerificationCode where Code=#{Code} and mail=#{mail} and " +
            "DATE_SUB(NOW(),INTERVAL 1 MINUTE) <= time")
    UserVerificationCode checkVerificationCode(UserVerificationCode userVerificationCode);
    @Insert("Insert into UserVerificationCode values(#{mail},#{Code},NOW())")
    void AddUserCode(UserVerificationCode userVerificationCode);
    @Update("Update UserVerificationCode set Code=#{Code} , time=NOW() where mail=#{mail}")
    void UpdateUserCode(UserVerificationCode userVerificationCode);
    @Select("Select * from UserVerificationCode where mail=#{mail}")
    UserVerificationCode checkExist(UserVerificationCode userVerificationCode);
}

