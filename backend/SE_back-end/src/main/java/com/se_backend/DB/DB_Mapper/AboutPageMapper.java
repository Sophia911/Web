package com.se_backend.DB.DB_Mapper;
import com.se_backend.DB.DB_object.AboutHead;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface AboutPageMapper {
     @Select("select id from Theme where name=#{theme_name}")
     int get_themeID(String theme_name);
     @Select("select AboutPageID from Webpage where themeID=#{themeID}")
     int get_aboutPageID(int themeID);
     @Select("select backgroundImg,title,description from AboutPage where aboutPageId = #{aboutPageId}")
     AboutHead select_aboutHead(AboutHead aboutHead);

}