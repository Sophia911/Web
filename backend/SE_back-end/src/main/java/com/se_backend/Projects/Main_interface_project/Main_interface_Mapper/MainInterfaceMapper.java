package com.se_backend.Projects.Main_interface_project.Main_interface_Mapper;

import com.se_backend.DB.DB_object.VideoPage;
import com.se_backend.Projects.Main_interface_project.Main_interface_Object.MainInterface;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface MainInterfaceMapper {
    @Select("Select * from BaseImg")
    ArrayList<MainInterface> SelectAllUrl();
    @Select("Select name from Theme")
    ArrayList<String> SelectThemes();
    @Select("Select id from Theme where name=#{name} limit 1")
    Integer getThemeID(@Param("name")String name);
    @Select("Select VideoPageID from Webpage where themeID=#{themeID} limit 1")
    Integer getVideoPageID(@Param("themeID")int themeID);
    @Select("Select * from VideoPage where id=#{id}")
    VideoPage getVideoPage(@Param("id")int id);
}
