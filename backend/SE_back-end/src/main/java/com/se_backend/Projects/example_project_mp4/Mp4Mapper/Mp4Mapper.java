package com.se_backend.Projects.example_project_mp4.Mp4Mapper;

import com.se_backend.Projects.example_project_mp4.Mp4Object.Mp4;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface Mp4Mapper {
    @Select("Select id_mp4,name,path from example_mp4")
    ArrayList<Mp4> Select_all();
    @Insert("Insert into example_mp4 values( null,#{name},#{path})")
    int Insert_img(Mp4 mp4);
    @Delete("Delete from example_mp4 where id_mp4=#{id_mp4}")
    int Delete_img(@Param("id_mp4")int id_mp4);
    @Select("Select OpState from OperationControl where Operation = 'UpLoadMp4s'")
    String get_Mp4s_status();
}
