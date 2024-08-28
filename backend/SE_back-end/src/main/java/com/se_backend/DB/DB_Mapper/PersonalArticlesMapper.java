package com.se_backend.DB.DB_Mapper;

import com.se_backend.DB.DB_object.PersonalArticles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
@Mapper
@Transactional
public interface PersonalArticlesMapper {
    @Insert("Insert into PersonalArticles values(#{title},#{content},NOW(),#{author},null)")
    void addPersonalArticles(PersonalArticles personalArticles);
    @Select("Select * from PersonalArticles")
    ArrayList<PersonalArticles> getAllPersonalArticles();
    @Select("Select * from PersonalArticles where title=#{title} and author=#{author} limit 1")
    PersonalArticles checkPersonalArticlesExist(PersonalArticles personalArticles);
    @Update("Update PersonalArticles set time=NOW(),content=#{content} where " +
            "author=#{author} and title=#{title}")
    void updatePersonalArticles(PersonalArticles personalArticles);
}
