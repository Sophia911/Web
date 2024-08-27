package com.se_backend.Projects.Backstage;

import com.se_backend.DB.DB_Mapper.RoleMapper;
import com.se_backend.DB.DB_Mapper.UserRoleMapper;
import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.UserRole;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginRequest;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginResult;
import com.se_backend.Projects.Backstage.Object.BackstageUserInfo.BackstageUserInfo;
import com.se_backend.Projects.Backstage.Object.BackstageUserInfo.BackstageUserInfoResult;
import com.se_backend.Projects.User.UserMapper;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/Backstage")
public class BackstageController {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @PostMapping("/Login")
    Result login(@RequestBody BackstageLoginRequest backstageLoginRequest) {
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

            return new Result("200", "登录成功", new BackstageLoginResult("20000", userToken.getToken()));
        } else {
            return new Result("401", "登录失败", new BackstageLoginResult("50008", "error"));
        }
    }

    @PostMapping("/GetUserInfoAll")
    Result getUserInfo_all(@RequestBody UserToken userToken) {
        ArrayList<UserRole> userRoleArrayList = userRoleMapper.getUserRole_all();
        ArrayList<BackstageUserInfo> backstageUserInfoArrayList = new ArrayList<>();
        for (UserRole userRole : userRoleArrayList) {
            BackstageUserInfo backstageUserInfo =
                    new BackstageUserInfo(userRole.getUserID(),
                            roleMapper.getName(userRole.getRoleID()));
            backstageUserInfoArrayList.add(backstageUserInfo);
        }
        return new Result(
                "200",
                "获取成功",
                new BackstageUserInfoResult(backstageUserInfoArrayList.size(), backstageUserInfoArrayList));
    }

}
