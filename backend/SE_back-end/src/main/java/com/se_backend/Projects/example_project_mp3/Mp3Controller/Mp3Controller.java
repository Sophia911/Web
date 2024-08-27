package com.se_backend.Projects.example_project_mp3.Mp3Controller;

import com.se_backend.Projects.example_project_mp3.Mp3Mapper.Mp3Mapper;
import com.se_backend.Projects.example_project_mp3.Mp3Object.Mp3;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/mp3")
public class Mp3Controller {
    private String BASE_PATH =  "src/resources/mp3s/";
    @Autowired
    private Mp3Mapper Mp3ControllerMapper;
    @GetMapping
    Result gat_all(){
        return new Result("msg","0000", Mp3ControllerMapper.Select_all());
    }
    @PostMapping
    Result upload(@RequestParam("Mp3File") MultipartFile Mp3File,
                  @RequestParam("count")int count) throws IOException {

        String state = Mp3ControllerMapper.get_Mp3s_status();
        if(state!=null && state.equals("TRUE")) {
            if (Mp3File == null) {
                return new Result("empty", "00001", null);
            }
            String filename = Mp3File.getOriginalFilename();
            String newFileName = count + filename;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String day = (calendar.get(Calendar.DAY_OF_WEEK) - 1) + "/";
            File filePath = new File(BASE_PATH + day);
            // 转为绝对路径
            File dest = new File(filePath.getAbsolutePath(), newFileName);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            System.out.println("音频上传服务器！");
            Mp3File.transferTo(dest);
            System.out.println("音频上传数据库！");
            return new Result("empty", "00001",
                    Mp3ControllerMapper.Insert_img(new Mp3(0, filename,
                            "http://81.68.84.163:8080/mp3s/" + day + newFileName)));
        }
        return  new Result("empty","00001","操作禁止");
    }
    @PostMapping("/delete")
    Result delete(@RequestBody Mp3 mp3){
        return new Result("删除","00001", Mp3ControllerMapper.Delete_img(mp3.getId_Mp3()));
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in MP3");
        System.out.println(ex.toString());
        return new Result("音频有异常！","00000",null) ;
    }
}
