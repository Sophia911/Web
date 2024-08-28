package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.OfficialMails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OfficialMailsMapper {
    @Select("Select * from OfficialMails where Label = #{Label} limit 1")
    OfficialMails getOfficialMailsByLabel(@Param("Label") String Label);
    @Select("Select MailboxVerification from User where id = #{id}")
    String checkMailboxVerification(@Param("id") String id);
}
