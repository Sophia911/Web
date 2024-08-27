package com.se_backend.DB.DB_Mapper;
import com.se_backend.DB.DB_object.ModuleInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Mapper
@Transactional
public interface ModuleInfoMapper {
    @Select("select img1,img2,img3,introduce from Modular where AboutPageID = #{aboutID}")
    ArrayList<ModuleInfo> select_modulesInfo_byID(int aboutID);

}