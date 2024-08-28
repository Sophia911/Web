package com.se_backend.Projects.Backstage;

import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginRequest;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginResult;
import com.se_backend.Projects.Backstage.Object.BackstageResult;
import com.se_backend.Projects.User.UserMapper;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vue-element/user")
public class BackstageUserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    @PostMapping("/login")
    Result login(@RequestBody BackstageLoginRequest backstageLoginRequest){
        String password = userMapper.getPasswordByEmail(backstageLoginRequest.getUsername());
        if(password == null){
            BackstageResult backstageResult = new BackstageResult(60002, "未注册的邮箱", null);
            return new Result("邮箱未注册", "400", backstageResult);
        }
        System.out.println(password);
        if (userMapper.getPasswordByEmail(backstageLoginRequest.getUsername())
                .equals(backstageLoginRequest.getPasswd())) {
            UUID uuid = UUID.randomUUID();
            UserToken userToken = new UserToken();
            userToken.setId(backstageLoginRequest.getUsername());
            userToken.setToken(uuid.toString());
            if (userTokenMapper.checkAccount(userToken) != null) {
                userTokenMapper.UpdateToken(userToken);//再次登录，重置登录状态
            } else {
                userTokenMapper.SaveToken(userToken);//首次登录，插入登录状态
            }
            BackstageResult result = new BackstageResult(20000, "登录成功", userToken.getToken());
            return new Result("登录成功", "200", result);
        } else {
            BackstageResult result = new BackstageResult(60204, "密码错误", null);
            return new Result("密码错误", "401", result);
        }
    }

    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in BackstageUser");
        System.out.println(ex.toString());
        return new Result("error","400",null) ;
    }

}
