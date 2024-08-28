package com.se_backend.Projects.Main_interface_project.Main_interface_Mapper;

import com.se_backend.Projects.Main_interface_project.Main_interface_Object.VideoInterface;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Mapper
@Transactional
public interface VideoInterfaceMapper {
    @Select("Select * from Video where AuditStatus='pass' order by rand() limit 20")
    ArrayList<VideoInterface> get_Some_video();
}
