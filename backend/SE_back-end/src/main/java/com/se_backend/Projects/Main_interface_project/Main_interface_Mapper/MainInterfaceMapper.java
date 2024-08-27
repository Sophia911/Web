package com.se_backend.Projects.Main_interface_project.Main_interface_Mapper;

import com.se_backend.Projects.Main_interface_project.Main_interface_Object.MainInterface;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface MainInterfaceMapper {
    @Select("Select * from BaseImg")
    ArrayList<MainInterface> SelectAllUrl();
}
