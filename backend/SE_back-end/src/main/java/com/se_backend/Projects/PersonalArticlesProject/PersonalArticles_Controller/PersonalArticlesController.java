package com.se_backend.Projects.PersonalArticlesProject.PersonalArticles_Controller;

import com.se_backend.DB.DB_Mapper.PersonalArticlesMapper;
import com.se_backend.Projects.PersonalArticlesProject.PersonalArticles_Object.AddPage;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/PersonalArticles")
public class PersonalArticlesController {
    @Autowired
    private PersonalArticlesMapper personalArticlesMapper;
    @PostMapping("/getPersonalArticles")
    Result getPersonalArticles(){
        return new Result("ok","00001",personalArticlesMapper.getAllPersonalArticles());
    }
    @PostMapping("/addPersonalArticles")
    Result addPersonalArticles(@RequestBody AddPage addPage){
        if(personalArticlesMapper.checkPersonalArticlesExist(addPage.getPersonalArticle())!=null){
            personalArticlesMapper.updatePersonalArticles(addPage.getPersonalArticle());
        }else {
            personalArticlesMapper.addPersonalArticles(addPage.getPersonalArticle());
        }
        return new Result("ok","00001",null);
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in PersonalArticles");
        System.out.println(ex.toString());
        return new Result("PersonalArticles有异常！","00000",null) ;
    }
}
