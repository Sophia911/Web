package com.se_backend.DB.DB_Mapper;
import com.se_backend.DB.DB_object.AboutHead;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Mapper
@Transactional
public interface AboutMapper {
    @Select("Select background,title,describe from AboutPage")
    ArrayList<AboutHead> Select_head();

}