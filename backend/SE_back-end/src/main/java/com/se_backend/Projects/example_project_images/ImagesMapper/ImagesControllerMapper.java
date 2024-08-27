package com.se_backend.Projects.example_project_images.ImagesMapper;

import com.se_backend.Projects.example_project_images.ImagesObject.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface ImagesControllerMapper {
    @Select("Select id_images,name,path from example_images")
    ArrayList<Image> Select_all();
    @Insert("Insert into example_images values( null,#{name},#{path})")
    int Insert_img(Image image);
    @Delete("Delete from example_images where id_images=#{id_images}")
    int Delete_img(@Param("id_images")int id_images);
    @Select("Select OpState from OperationControl where Operation = 'UpLoadImgs'")
    String get_img_status();
}
