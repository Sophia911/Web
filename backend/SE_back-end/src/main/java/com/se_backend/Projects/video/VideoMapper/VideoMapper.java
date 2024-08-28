package com.se_backend.Projects.video.VideoMapper;

import com.se_backend.Projects.video.VideoObject.Video;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface VideoMapper {

    @Insert("Insert into Video values( null,#{owner_id},#{name},#{url},now(),#{type},#{pic_id},'wait','sys')")
    int Insert_video(Video video);

}
