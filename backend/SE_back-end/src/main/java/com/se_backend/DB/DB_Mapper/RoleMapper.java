package com.se_backend.DB.DB_Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select name from Role where id = #{id}")
    String getName(@Param("id") int id);
}
