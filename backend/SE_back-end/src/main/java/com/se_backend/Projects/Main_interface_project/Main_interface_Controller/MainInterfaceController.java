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
    @GetMapping
    Result get_all_url(){
        return new Result("ok","1000",mainInterfaceMapper.SelectAllUrl());
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in MainInterface");
        System.out.println(ex.toString());
        return new Result("MainInterface有异常！","00000",null) ;
    }
}
