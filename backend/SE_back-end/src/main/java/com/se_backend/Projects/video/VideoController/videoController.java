package com.se_backend.Projects.video.VideoController;

import com.se_backend.Projects.video.VideoMapper.VideoMapper;
import com.se_backend.Projects.video.VideoObject.Video;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/video")
public class videoController {
    private String BASE_PATH = "src/main/resources/Video/";
    private String BASE_PATH2 = "src/main/resources/VideoImg/";
    @Autowired
    private VideoMapper videoMapper;
    @PostMapping
    Result upload(@RequestParam("videoFile") MultipartFile videoFile,
                  @RequestParam("imgFile") MultipartFile imgFile,
                  @RequestParam("name") String name,
                  @RequestParam("owner_id") String  owner_id,
                  @RequestParam("type")  String type) throws IOException {
        System.out.println("ok");

            if (videoFile ==null||imgFile==null) {
                return new Result("empty","00001",null);
            }
        String filename = videoFile.getOriginalFilename();
        String filename2 = imgFile.getOriginalFilename();

//        String day = (calendar.get(Calendar.DAY_OF_WEEK)-1)+"/";
        File filePath = new File(BASE_PATH);
        File filePath2 = new File(BASE_PATH2);
        // 转为绝对路径
        File dest = new File(filePath.getAbsolutePath(),filename);
        File dest2 = new File(filePath2.getAbsolutePath(),filename2);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        if (!filePath2.exists()) {
            filePath2.mkdirs();
        }
        System.out.println(dest);
        System.out.println(dest2);
        System.out.println("视频上传服务器！");
        videoFile.transferTo(dest);
        System.out.println("图片上传服务器！");
        imgFile.transferTo(dest2);
        System.out.println("上传数据库！");
        return new Result("empty","00001", videoMapper
                .Insert_video(new Video(owner_id,name,"https://www.ichlearning.cn:8080/Video/"+filename,type,"https://www.ichlearning.cn:8080/VideoImg/"+filename2)));
    }

    @PostMapping("/suiban")
    Result upload(@RequestParam("videoFile") MultipartFile videoFile
                  ) {
        return new Result("okk","dc"," ");
    }

    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in video");
        System.out.println(ex.toString());
        return new Result("video有异常！","00000",null) ;
    }
}
