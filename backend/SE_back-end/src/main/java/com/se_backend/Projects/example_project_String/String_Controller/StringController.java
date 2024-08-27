package com.se_backend.Projects.example_project_String.String_Controller;

import com.se_backend.Projects.example_project_String.String_Mapper.StringMapper;
import com.se_backend.Projects.example_project_String.String_Object.StringObject;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/String_example")
public class StringController {
    @Autowired
    private StringMapper stringMapper;
    @GetMapping("/all")//查询全部
    Result Get_all_String(){
        return new Result("","",stringMapper.selectAll());
    }
    @PostMapping("/per")//查询单个
    Result Get_String(@RequestBody StringObject stringObject){
        return new Result("","",stringMapper.select_byID(stringObject.getId()));
    }
    @PostMapping//上传字符串
    Result Add_String(@RequestBody StringObject stringObject){
        return new Result("","",stringMapper.Insert(stringObject));
    }
    @PostMapping("/update")//更新字符串
    Result Update_String(@RequestBody StringObject stringObject){
        return new Result("","",stringMapper.Update(stringObject));
    }
    @PostMapping("/delete")//删除字符串
    Result Delete_String(@RequestBody StringObject stringObject){
        return new Result("","",stringMapper.Delete(stringObject));
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in String");
        System.out.println(ex.toString());
        return new Result("String有异常！","00000",null) ;
    }
}
