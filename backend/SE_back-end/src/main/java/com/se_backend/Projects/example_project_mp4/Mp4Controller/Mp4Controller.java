package com.se_backend.Projects.example_project_mp4.Mp4Controller;

import com.se_backend.Projects.example_project_images.ImagesObject.Image;
import com.se_backend.Projects.example_project_mp4.Mp4Mapper.Mp4Mapper;
import com.se_backend.Projects.example_project_mp4.Mp4Object.Mp4;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/mp4")
public class Mp4Controller {
    private String BASE_PATH = "src/resources/mp4s/";;
    @Autowired
    private Mp4Mapper mp4ControllerMapper;
    @GetMapping
    Result gat_all(){
        return new Result("msg","0000", mp4ControllerMapper.Select_all());
    }
    @PostMapping
    Result upload(@RequestParam("mp4File") MultipartFile mp4File,
                  @RequestParam("count")int count) throws IOException {

        String state = mp4ControllerMapper.get_Mp4s_status();
        if(state!=null && state.equals("TRUE")){
            if (mp4File ==null) {
                return new Result("empty","00001",null);
            }
        String filename = mp4File.getOriginalFilename();
        String newFileName =count + filename;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String day = (calendar.get(Calendar.DAY_OF_WEEK)-1)+"/";
        File filePath = new File(BASE_PATH+day);
        // 转为绝对路径
        File dest = new File(filePath.getAbsolutePath(),newFileName);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        System.out.println("视频上传服务器！");
        mp4File.transferTo(dest);
        System.out.println("视频上传数据库！");
        return new Result("empty","00001",mp4ControllerMapper
                .Insert_img(new Mp4(0,filename,"http://81.68.84.163:8080/mp4s/"+day+newFileName)));
        }
        return  new Result("empty","00001","操作禁止");
    }
    @PostMapping("/delete")
    Result delete(@RequestBody Mp4 mp4){
        return new Result("删除","00001", mp4ControllerMapper.Delete_img(mp4.getId_Mp4()));
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in images");
        System.out.println(ex.toString());
        return new Result("mp4有异常！","00000",null) ;
    }
}
