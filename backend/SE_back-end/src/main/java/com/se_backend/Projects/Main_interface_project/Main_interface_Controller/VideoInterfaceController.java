package com.se_backend.Projects.Main_interface_project.Main_interface_Controller;

import com.se_backend.Projects.Main_interface_project.Main_interface_Mapper.VideoInterfaceMapper;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/VideoInterface")
public class VideoInterfaceController {
    @Autowired
    private VideoInterfaceMapper videoInterfaceMapper;
    @GetMapping
    Result get_VideoInterface(){
        return new Result("ok","0000",videoInterfaceMapper.get_Some_video());
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in VideoInterface");
        System.out.println(ex.toString());
        return new Result("VideoInterface有异常！","00000",null) ;
    }
}
