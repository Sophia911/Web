package com.se_backend.Projects.About_page_project;

import com.se_backend.DB.DB_Mapper.AboutPageMapper;

import com.se_backend.DB.DB_Mapper.ModuleInfoMapper;
import com.se_backend.DB.DB_object.AboutHead;
import com.se_backend.DB.DB_object.ModuleInfo;
import com.se_backend.DB.DB_object.ThemeInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/GetAboutInfo")
public class AboutController{
    @Autowired
    private AboutPageMapper aboutPageMapper;
    @Autowired
    private ModuleInfoMapper moduleInfoMapper;
    @PostMapping
    AboutInfo get_about_info(@RequestBody ThemeInfo themeInfo ){
        String theme_name=themeInfo.getThemeName();
        AboutInfo aboutInfo=new AboutInfo();
        System.out.println(theme_name);
        int themeID = aboutPageMapper.get_themeID(theme_name);
        System.out.println(themeID);
        int aboutID= aboutPageMapper.get_aboutPageID(themeID);
        System.out.println(aboutID);

        AboutHead aboutHead=new AboutHead();
        aboutHead.set_Id(aboutID);
        aboutHead=aboutPageMapper.select_aboutHead(aboutHead);
        System.out.println("yes i got it !aboutID");
        aboutInfo.set_background(aboutHead.get_background());
        aboutInfo.set_title(aboutHead.get_title());
        aboutInfo.set_describe(aboutHead.get_description());

        ArrayList<ModuleInfo> tempShowList=new ArrayList<>();
        for(ModuleInfo perModuleInfo:moduleInfoMapper.select_modulesInfo_byID(aboutID)
        ){
            tempShowList.add(new ModuleInfo(
                    perModuleInfo.getImage1(),
                    perModuleInfo.getImage2(),
                    perModuleInfo.getImage3(),
                    perModuleInfo.getIntroduce()));
        }
        aboutInfo.set_showList(tempShowList);
        return aboutInfo;
    }
}
