package com.se_backend.Projects.example_project_String.String_Mapper;

import com.se_backend.Projects.example_project_String.String_Object.StringObject;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface StringMapper {
    @Insert("Insert into example_String values(null,#{value})")
    int Insert(StringObject stringObject);
    @Delete("Delete from example_String where id_String = #{id}")
    int Delete(StringObject stringObject);
    @Select("Select id_String as id,value from example_String")
    ArrayList<StringObject> selectAll();
    @Select("Select value from example_String where id_String = #{id_String}")
    String select_byID(@Param("id_String")int id_String);
    @Update("Update example_String set value = #{value} where id_String = #{id}")
    int Update(StringObject stringObject);
}
