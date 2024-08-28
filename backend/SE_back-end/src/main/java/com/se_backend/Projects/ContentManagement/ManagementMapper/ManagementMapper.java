package com.se_backend.Projects.ContentManagement.ManagementMapper;

import com.se_backend.DB.DB_object.*;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface ManagementMapper {
    @Select("Select id from Theme where name=#{name}")
    Integer getThemeIDbyName(@Param("name")String name);
    @Select("Select * from Webpage where themeID=#{themeID}")
    Webpage getWebpage(@Param("themeID")Integer themeID);
    @Select("Select id from VideoPage where backgroundImg=#{backgroundImg} and carouselImg_1=#{carouselImg_1} and " +
            "carouselImg_2=#{carouselImg_2} and carouselImg_3=#{carouselImg_3} and carouselImg_4=#{carouselImg_4} and " +
            "videoBackGroundImg=#{videoBackGroundImg} and carouselImg_5=#{carouselImg_5} and videoRecommendImg=#{videoRecommendImg} limit 1")
    Integer getVideoPageIDByInforms(VideoPage videoPage);
    @Select("Select aboutPageId from AboutPage where title=#{title}")
    Integer getAboutPageIDByTitle(@Param("title")String title);
    @Select("Select * from Video where AuditStatus='wait' and ReviewedBy=#{Reviewer} limit 5")
    ArrayList<Video> getWaitVideo(@Param("Reviewer")String Reviewer);

    @Update("Update AboutPage set " +
            "title=#{title},description=#{description},backgroundImg=#{backgroundImg}" +
            " where aboutPageId=#{AboutPageID}")
    void updateAboutPage(@Param("AboutPageID")Integer AboutPageID,
                         @Param("title")String title,
                         @Param("description")String description,
                         @Param("backgroundImg")String backgroundImg);
    @Update("Update VideoPage set backgroundImg=#{backgroundImg},carouselImg_1=#{carouselImg_1}, " +
            " carouselImg_2=#{carouselImg_2},carouselImg_3=#{carouselImg_3},carouselImg_4=#{carouselImg_4}," +
            "videoBackGroundImg=#{videoBackGroundImg},carouselImg_5=#{carouselImg_5},videoRecommendImg=#{videoRecommendImg} " +
            "where id=#{id}")
    void updateVideoPage(VideoPage videoPage);
    @Update("Update Webpage set VideoPageID=#{VideoPageID} where id=#{id}")
    void WebPageSetVideoPageID(@Param("VideoPageID")Integer VideoPageID,@Param("id")Integer id);
    @Update("Update Webpage set AboutPageID=#{AboutPageID} where id=#{id}")
    void WebPageSetAboutPageID(@Param("AboutPageID")Integer AboutPageID,@Param("id")Integer id);
    @Update("Update Video set ReviewedBy=#{Reviewer} where id=#{id}")
    void ReviewerGetJob(@Param("Reviewer")String Reviewer,@Param("id")Integer id);
    @Update("Update Video set ReviewedBy=#{Reviewer},AuditStatus='pass' where id=#{id}")
    void VideoReviewedPass(@Param("Reviewer")String Reviewer,@Param("id")Integer id);
    @Update("Update Video set ReviewedBy=#{Reviewer},AuditStatus='reject' where id=#{id}")
    void VideoReviewedReject(@Param("Reviewer")String Reviewer,@Param("id")Integer id);

    @Insert("Insert into Theme values(null,#{name})")
    void InsertTheme(@Param("name")String name);
    @Insert("Insert into Webpage values(null,#{themeID},null,#{AboutPageID})")
    void InsertWebPageAboutPage(Webpage webpage);
    @Insert("Insert into Webpage values(null,#{themeID},#{VideoPageID},null)")
    void InsertWebPageVideoPage(Webpage webpage);
    @Insert("Insert into Modular values(null,#{img1},#{img2},#{img3},#{introduce},#{AboutPageID})")
    void InsertModular(Modular modular);
    @Insert("Insert into AboutPage values(null,#{title},#{description},#{backgroundImg})")
    void InsertAboutPage(AboutHead aboutHead);
    @Insert("Insert into VideoPage values(null,#{backgroundImg},#{carouselImg_1}," +
            "#{carouselImg_2},#{carouselImg_3},#{carouselImg_4},#{videoBackGroundImg}," +
            "#{carouselImg_5},#{videoRecommendImg})")
    void InsertVideoPage(VideoPage videoPage);

    @Delete("Delete from Modular where AboutPageID=#{AboutPageID}")
    void deleteModular(@Param("AboutPageID")Integer AboutPageID);
}
