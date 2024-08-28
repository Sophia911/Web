package com.se_backend.Projects.video.VideoController;

import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.URLPowers;
import com.se_backend.DB.DB_object.UserRole;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorRolePowerMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorURLPowerNeedMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorUerRoleMapper;
import com.se_backend.Projects.video.VideoMapper.VideoMapper;
import com.se_backend.Projects.video.VideoObject.Video;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/video")
public class videoController {
    private String BASE_PATH = "src/main/resources/Video/";
    private String BASE_PATH2 = "src/main/resources/VideoImg/";
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private InterceptorUerRoleMapper interceptorUerRoleMapper;
    @Autowired
    private InterceptorRolePowerMapper interceptorRolePowerMapper;
    @Autowired
    private InterceptorURLPowerNeedMapper interceptorURLPowerNeedMapper;
    boolean powerCheck(String id,String token,String URL,String Method){
        //验证密钥
        if(userTokenMapper.CheckToken(new UserToken(id,token))!=null){
            ArrayList<UserRole> UerRoles = interceptorUerRoleMapper.getUserAllRole(id);
            ArrayList<Integer> PowerID;
            Map<Integer,Boolean> powerMap = new HashMap<>();
            //生成用户权限哈希表
            for (UserRole UerRole:UerRoles
            ) {
                PowerID = interceptorRolePowerMapper.GetPowerID(UerRole.getRoleID());
                for (Integer powerID:PowerID
                ) {
                    if(!powerMap.containsKey(powerID)){
                        powerMap.put(powerID,true);
                    }
                }
            }
            //获取路由+方法所需权限清单
            ArrayList<URLPowers> Powers= interceptorURLPowerNeedMapper.GetURLPowers(URL,Method);
            //验证路由+方法权限与用户权限是否匹配
            for (URLPowers power:Powers
            ) {
                if(!powerMap.containsKey(power.getPowerNeed())) {
                    System.out.println("权限验证失败 "+power.getPowerNeed());
                    return false;
                }
            }
            System.out.println("权限验证通过 ");
            return true;
        }
        System.out.println("用户密钥验证失败 ");
        return false;
    }

    @PostMapping
    Result upload(
                  @RequestParam("id") String id,
                  @RequestParam("token") String token,
                  @RequestParam("videoFile") MultipartFile videoFile,
                  @RequestParam("imgFile") MultipartFile imgFile,
                  @RequestParam("name") String name,
                  @RequestParam("owner_id") String  owner_id,
                  @RequestParam("type")  String type) throws IOException {


            if(!powerCheck(id,token,"/video","POST")){
                return new Result("权限不足","00001",false);
            }
            if (videoFile ==null||imgFile==null) {
                return new Result("文件为空","00001",false);
            }
        String filename = videoFile.getOriginalFilename();
        String filename2 = imgFile.getOriginalFilename();

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
        return new Result("上传失败","00001", videoMapper
                .Insert_video(new Video(owner_id,name,"http://localhost:8080/Video/"+filename,type,"http://localhost:8080/VideoImg/"+filename2)));
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
