package com.se_backend.Projects.Main_interface_project.Main_interface_Controller;

import com.se_backend.Projects.Main_interface_project.Main_interface_Mapper.MainInterfaceMapper;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/MainInterface")
public class MainInterfaceController {
    @Autowired
    private MainInterfaceMapper mainInterfaceMapper;
    @PostMapping("/getBaseURL")
    Result getBaseURL(@RequestBody theme theme2){
        System.out.println("theme:"+theme2);
        Integer ThemeID = mainInterfaceMapper.getThemeID(theme2.Theme);
        if(ThemeID == null)ThemeID = 1;
        Integer VideoID = mainInterfaceMapper.getVideoPageID(ThemeID);
        if(VideoID == null) VideoID = 1;
        System.out.println("VideoID:"+VideoID);
        return new Result("ok","1000",mainInterfaceMapper.getVideoPage(VideoID));
    }
    @GetMapping("/getThemes")
    Result getTheme(){
        return new Result("ok","1000",mainInterfaceMapper.SelectThemes());
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in MainInterface");
        System.out.println(ex.toString());
        return new Result("MainInterface有异常！","00000",null) ;
    }
}
