package com.se_backend.Projects.ContentManagement.ManagementMapper.ManagementController;

import com.se_backend.DB.DB_Mapper.OfficialMailsMapper;
import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_Mapper.UserVerificationCodeMapper;
import com.se_backend.DB.DB_object.*;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorRolePowerMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorURLPowerNeedMapper;
import com.se_backend.Interceptor.InterceptorMapper.InterceptorUerRoleMapper;

import com.se_backend.Projects.ContentManagement.ManagementMapper.ManagementMapper;
import com.se_backend.Projects.ContentManagement.ManagementObject.*;
import com.se_backend.Result;

import com.se_backend.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ContentManagement")
public class ManagementController {
    @Autowired
    private ManagementMapper managementMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private OfficialMailsMapper officialMailsMapper;
    @Autowired
    private UserVerificationCodeMapper userVerificationCodeMapper;
    @Autowired
    private InterceptorUerRoleMapper interceptorUerRoleMapper;
    @Autowired
    private InterceptorRolePowerMapper interceptorRolePowerMapper;
    @Autowired
    private InterceptorURLPowerNeedMapper interceptorURLPowerNeedMapper;
    String BASE_PATH_ABOUT ="src/main/resources/AboutImages/";
    String BASE_PATH_THEME = "src/main/resources/MainInterfaceImages/";
    String BASE_URL_ABOUT = "http://localhost:8080/AboutImages/";
    String BASE_URL_THEME = "http://localhost:8080/MainInterfaceImages/";
    String createFile(MultipartFile file,String basePath,String baseURl) throws IOException {
        String filename = file.getOriginalFilename();
        File filePath = new File(basePath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File dest = new File(filePath.getAbsolutePath(),filename);
        file.transferTo(dest);
        return baseURl+filename;
    }
    //校验权限与密钥，为了传输文件无法使用 RequestBody 故处理此特例
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

    boolean addNewModule(Integer AboutPageID,ArrayList<String> contentList,ArrayList<String> imgFiles){
        for (int i=0;i<contentList.size();i++){
            Modular modular = new Modular();
            modular.setAboutPageID(AboutPageID);
            modular.setImg1(imgFiles.get(i*3));
            modular.setImg2(imgFiles.get(i*3+1));
            modular.setImg3(imgFiles.get(i*3+2));
            modular.setIntroduce(contentList.get(i));
            managementMapper.InsertModular(modular);
        }//添加新的模块
        return true;
    }
    boolean updateAboutPage(Webpage webpage,String title,String content,String topicImg,
                            ArrayList<String> contentList,ArrayList<String> imgFiles){
        if(webpage.getAboutPageID()!=null){
        managementMapper.deleteModular(webpage.getAboutPageID());//删除原有的模块
        managementMapper.updateAboutPage(webpage.getAboutPageID(),title,content,topicImg);//修改标题
        addNewModule(webpage.getAboutPageID(),contentList,imgFiles);//添加新的模块
        }else {
            AboutHead aboutHead = new AboutHead(0,title,content,topicImg);
            managementMapper.InsertAboutPage(aboutHead);
            Integer AboutPageID = managementMapper.getAboutPageIDByTitle(title);
            addNewModule(AboutPageID,contentList,imgFiles);//添加新的模块
            managementMapper.WebPageSetAboutPageID(AboutPageID,webpage.getId());
        }
        return true;
    }
    boolean createAboutPage(String theme,String title,String content,String topicImg,
                            ArrayList<String> contentList,ArrayList<String> imgFiles){
        AboutHead aboutHead = new AboutHead(0,title,content,topicImg);
        managementMapper.InsertTheme(theme);
        managementMapper.InsertAboutPage(aboutHead);
        Integer AboutPageID = managementMapper.getAboutPageIDByTitle(title);
        Integer themeID = managementMapper.getThemeIDbyName(theme);
        addNewModule(AboutPageID,contentList,imgFiles);//添加新的模块
        Webpage webpage = new Webpage();
        webpage.setAboutPageID(AboutPageID);
        webpage.setVideoPageID(1);
        webpage.setThemeID(themeID);
        managementMapper.InsertWebPageAboutPage(webpage);
        return true;
    }
    boolean updateVideoPage(Webpage webpage,VideoPage videoPage){
        if(webpage.getVideoPageID()==null){
            managementMapper.InsertVideoPage(videoPage);
            Integer videoPageID = managementMapper.getVideoPageIDByInforms(videoPage);
            managementMapper.WebPageSetVideoPageID(videoPageID,webpage.getId());
        }else{
            videoPage.setId(webpage.getVideoPageID());
            managementMapper.updateVideoPage(videoPage);
        }
        return true;
    }
    boolean createVideoPage(String theme, VideoPage videoPage){
        managementMapper.InsertVideoPage(videoPage);
        managementMapper.InsertTheme(theme);
        Integer themeID = managementMapper.getThemeIDbyName(theme);
        Integer videoPageID = managementMapper.getVideoPageIDByInforms(videoPage);
        Webpage webpage = new Webpage();
        webpage.setVideoPageID(videoPageID);
        webpage.setThemeID(themeID);
        managementMapper.InsertWebPageVideoPage(webpage);
        return true;
    }
    //上传关于页面内容
    @PostMapping("/About")
    Result ManagementAboutPage(
                               @RequestParam("id") String id
                               ,@RequestParam("token") String token
                               ,@RequestParam("theme") String theme
                               ,@RequestParam("title") String title
                               ,@RequestParam("content") String content
                               ,@RequestParam("backImg")MultipartFile topicImg
                               ,@RequestParam("numbers[]")ArrayList<Integer> numbers
                               ,@RequestParam("contentList[]")ArrayList<String> contentList
                               ,@RequestParam("imgFiles[]")ArrayList<MultipartFile> imgFiles
                               ) throws IOException {
        if(powerCheck(id,token,"/ContentManagement/About","POST")){
            String topicURL = createFile(topicImg,BASE_PATH_ABOUT,BASE_URL_ABOUT);
            ArrayList<String> imgFilesURL = new ArrayList<>();
            for (MultipartFile file:imgFiles
            ) {
                imgFilesURL.add(createFile(file,BASE_PATH_ABOUT,BASE_URL_ABOUT));
            }
            Integer themeID = managementMapper.getThemeIDbyName(theme);
            if(themeID==null){
                if(!createAboutPage(theme,title,content,topicURL,contentList,imgFilesURL))
                    return new Result("false","00000",null);
            }else{
                Webpage webpage = managementMapper.getWebpage(themeID);
                if(!updateAboutPage(webpage,title,content,topicURL,contentList,imgFilesURL))
                    return new Result("false","00000",null);
            }
            return new Result("ok","1000",null);
        }
        return new Result("false","00000",null);
    }
    @PostMapping("/Theme")
    //上传主题页面内容
    Result ManagementThemePage(
            @RequestParam("id") String id
            ,@RequestParam("token") String token
            ,@RequestParam("theme") String theme
            ,@RequestParam("carouselChartFiles[]")ArrayList<MultipartFile> carouselChartFiles
            ,@RequestParam("backgroundsFiles[]")ArrayList<MultipartFile> backgroundsFiles) throws IOException {
        if(powerCheck(id,token,"/ContentManagement/Theme","POST")){
            ArrayList<String> carouselChartFilesURL = new ArrayList<>();
            ArrayList<String> backgroundsFilesURL = new ArrayList<>();
            for (MultipartFile file :carouselChartFiles
            ) {
                carouselChartFilesURL.add(createFile(file,BASE_PATH_THEME,BASE_URL_THEME));
            }
            for (MultipartFile file :backgroundsFiles
            ) {
                backgroundsFilesURL.add(createFile(file,BASE_PATH_THEME,BASE_URL_THEME));
            }
            VideoPage videoPage = new VideoPage(
                    backgroundsFilesURL.get(0),
                    backgroundsFilesURL.get(1),
                    backgroundsFilesURL.get(2),
                    carouselChartFilesURL.get(0),
                    carouselChartFilesURL.get(1),
                    carouselChartFilesURL.get(2),
                    carouselChartFilesURL.get(3),
                    carouselChartFilesURL.get(4));
            Integer themeID = managementMapper.getThemeIDbyName(theme);
            if(themeID==null){
                if(!createVideoPage(theme,videoPage))
                    return new Result("false","00000",null);
            }else {
                Webpage webpage = managementMapper.getWebpage(themeID);
                if(!updateVideoPage(webpage,videoPage))
                    return new Result("false","00000",null);
            }
            return new Result("ok","1000",null);
        }
        return new Result("false","00000",null);
    }
    //获取视频审核内容
    @PostMapping("/Video/getJobs")
    Result getWaitVideo(@RequestBody Reviewer reviewer){
        ArrayList<Video> jobs = managementMapper.getWaitVideo(reviewer.getUser());
        if(jobs.size()==0){
            jobs = managementMapper.getWaitVideo("sys");
            for (Video video:jobs
                 ) {
                managementMapper.ReviewerGetJob(reviewer.getUser(),video.getId());
            }
        }
        return new Result("ok","1000",jobs);
    }
    @PostMapping("/Video/VideoReviewedPass")
    Result VideoReviewed(@RequestBody VideoReviewed videoReviewed){
        managementMapper.VideoReviewedPass(videoReviewed.getUser(),videoReviewed.getId());
        return new Result("ok","00000",null);
    }
    @PostMapping("/Video/VideoReviewedReject")
    Result VideoReviewedReject(@RequestBody VideoReviewed videoReviewed){
        managementMapper.VideoReviewedReject(videoReviewed.getUser(),videoReviewed.getId());
        return new Result("ok","00000",null);
    }
    @PostMapping("/getVerificationCode")
    Result SentMailUrl(@RequestBody GetVerificationCode getVerificationCode) throws IOException {
        if(Objects.equals(officialMailsMapper.checkMailboxVerification(getVerificationCode.getMail()), "true")){
            String subject="非遗学习网";
            int code = (int)((Math.random()*9+1)*100000);
            String content = "【非遗学习网】验证码："+ code +"，您正在申请网页内容管理 (若非本人操作，请删除本邮件)";
            UserVerificationCode userVerificationCodeBuild = new UserVerificationCode(getVerificationCode.getMail(),code);
            UserVerificationCode userVerificationCode = userVerificationCodeMapper.checkExist(userVerificationCodeBuild);
            if(userVerificationCode!=null){
                userVerificationCodeMapper.UpdateUserCode(userVerificationCodeBuild);
            }else{
                userVerificationCodeMapper.AddUserCode(userVerificationCodeBuild);
            }
            OfficialMails officialMails = officialMailsMapper.getOfficialMailsByLabel("sys");
            return new Result("网络错误","0000",SendMail.Sent(getVerificationCode.getMail(),subject,content,officialMails));
        }else {
            return new Result("邮箱未验证","0000",false);
        }
    }
    @PostMapping("/checkVerificationCode")
    Result CheckVerificationCodeUrl(@RequestBody CheckVerificationCode checkVerificationCode){
        UserVerificationCode userVerificationCode = userVerificationCodeMapper.checkVerificationCode(checkVerificationCode.getUserVerificationCode());
        if(userVerificationCode==null) {
            return new Result("", "0000", false);
        }else {
            return new Result("", "0000", true);
        }
    }
    @PostMapping("/GetRole")
    Result getUserRole(@RequestBody GetUserRole getUserRole){
        ArrayList<UserRole> userRoles = interceptorUerRoleMapper.getUserAllRole(getUserRole.getUserPowerCheck().getId());
        ArrayList<String> Roles = new ArrayList<>();
        for (UserRole Role:userRoles
             ) {
            Roles.add(interceptorUerRoleMapper.getUserRoleName(Role.getRoleID()));
        }
        if(Roles.isEmpty()){
            return new Result("未分配角色","0000",null);
        }
        return new Result("ok","0000",Roles);
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in ContentManagement");
        System.out.println(ex.toString());
        return new Result("ContentManagement有异常！","00000",null) ;
    }
}
