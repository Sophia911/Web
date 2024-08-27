package com.se_backend.Projects.example_project_images.ImagesController;

import com.se_backend.Projects.example_project_images.ImagesMapper.ImagesControllerMapper;
import com.se_backend.Projects.example_project_images.ImagesObject.Image;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/images")
public class ImagesController {
    private String BASE_PATH = "src/resources/images/";
    @Autowired
    private ImagesControllerMapper imagesControllerMapper;
    @GetMapping
    Result get_all(){
        return new Result("msg","0000",imagesControllerMapper.Select_all());
    }
    @PostMapping
    Result upload(@RequestParam("imageFile") MultipartFile imageFile,
                         @RequestParam("count")int count) throws IOException {
        String state = imagesControllerMapper.get_img_status();
        if(state!=null && state.equals("TRUE")) {
            if (imageFile == null) {
                return new Result("empty", "00001", null);
            }
            String filename = imageFile.getOriginalFilename();
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
            System.out.println("图片上传服务器！");
            imageFile.transferTo(dest);
            System.out.println("图片上传数据库！");
            return new Result("empty", "00001", imagesControllerMapper
                    .Insert_img(new Image(0, filename, "http://81.68.84.163:8080/images/" + day + newFileName)));
        }
        return  new Result("empty","00001","操作禁止");
    }
    @PostMapping("/delete")
    Result delete(@RequestBody Image image){
        return new Result("删除","00001",imagesControllerMapper.Delete_img(image.getId_images()));
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in images");
        System.out.println(ex.toString());
        return new Result("images有异常！","00000",null) ;
    }
}
