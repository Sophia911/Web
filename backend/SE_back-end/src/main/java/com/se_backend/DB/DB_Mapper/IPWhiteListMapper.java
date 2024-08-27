package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.IPWhiteList;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface IPWhiteListMapper {
    @Select("Select * from IPWhiteList")
    ArrayList<IPWhiteList>GetAllWhiteList();
    @Insert("Insert into IPWhiteList values(#{IP})")
    int AddIP(@Param("IP") String IP);
    @Delete("Delete from IPWhiteList where IP=#{IP}")
    int DeleteIP(@Param("IP") String IP);
}
