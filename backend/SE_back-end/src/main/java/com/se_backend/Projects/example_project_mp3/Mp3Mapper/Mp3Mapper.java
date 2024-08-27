package com.se_backend.Projects.example_project_mp3.Mp3Mapper;

import com.se_backend.Projects.example_project_mp3.Mp3Object.Mp3;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface Mp3Mapper {
    @Select("Select id_mp3,name,path from example_mp3")
    ArrayList<Mp3> Select_all();
    @Insert("Insert into example_mp3 values( null,#{name},#{path})")
    int Insert_img(Mp3 mp3);
    @Delete("Delete from example_mp3 where id_mp3=#{id_mp3}")
    int Delete_img(@Param("id_mp3")int id_mp3);
    @Select("Select OpState from OperationControl where Operation = 'UpLoadMp3s'")
    String get_Mp3s_status();
}
