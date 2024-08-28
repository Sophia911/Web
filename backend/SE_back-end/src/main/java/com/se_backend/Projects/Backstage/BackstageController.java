package com.se_backend.Projects.Backstage;

import com.alibaba.fastjson.JSONObject;
import com.se_backend.DB.DB_Mapper.BackstageUserMapper;
import com.se_backend.DB.DB_Mapper.RoleMapper;
import com.se_backend.DB.DB_Mapper.UserRoleMapper;
import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.BackstageUser;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginRequest;
import com.se_backend.Projects.Backstage.Object.BackstageLogin.BackstageLoginResult;
import com.se_backend.Projects.Backstage.Object.BackstagePowerChange;
import com.se_backend.Projects.Backstage.Object.BackstageResult;
import com.se_backend.Projects.Backstage.Object.BackstageUserInfo.BackstageUserInfo;
import com.se_backend.Projects.Backstage.Object.BackstageUserInfo.BackstageUserInfoResult;
import com.se_backend.Projects.User.UserMapper;
import com.se_backend.Projects.User.UserObject;
import com.se_backend.Projects.User_project.UserPage;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/vue-element-admin/user")
public class BackstageController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private BackstageUserMapper backstageUserMapper;

    HashMap<String, Integer> roles = new HashMap<String, Integer>(){
        {
            put("admin", 1);
            put("editor", 2);
            put("visitor", 3);
        }
    };

    @PostMapping("/login")
    Result login(@RequestBody BackstageLoginRequest backstageLoginRequest) {
        System.out.println("login attempt: ");
        System.out.println("username: " + backstageLoginRequest.getUsername());
        System.out.println("password: " + backstageLoginRequest.getPasswd());
        String password = userMapper.getPasswordByEmail(backstageLoginRequest.getUsername());
        if (password == null)
            return new Result("未注册的邮箱", "400", new BackstageLoginResult(60204, "error"));
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

            return new Result("登录成功", "200", new BackstageLoginResult(20000, userToken.getToken()));
        } else {
            return new Result("密码错误", "401", new BackstageLoginResult(50008, "error"));
        }
    }

    @PostMapping("/info")
    Result getUserinfo(String token) {
        System.out.println("token: " + token);
        String userID = userTokenMapper.searchUserByToken(token);
        if(userID == null)
            return new Result("token过期", "402",
                    new BackstageResult(50014, "error", null));
        UserPage user = userMapper.searchUser(userID);
        BackstageUserInfo userInfo = new BackstageUserInfo(user.getID(), null,
                user.getName(), user.avatar(), user.getIntroduction());
        ArrayList<String> Roles = userRoleMapper.getUserRole(userID);
        ArrayList<String> roleList = new ArrayList<>();
        for (String role : Roles){
            roleList.add(roleMapper.getRoleNameById(role));
        }
        userInfo.setRoles(roleList);
        userInfo.printUserInfo();
        return new Result("get user info", "50008",
                new BackstageResult(20000, "ok", userInfo));
    }

    @PostMapping("/all")
    Result getAllUsersInformation(String token){
        String userID = userTokenMapper.searchUserByToken(token);
        System.out.println(userID);
        if(userID == null)
            return new Result("token无效", "502",
                    new BackstageResult(50008, "token error", null));
        ArrayList<String> Roles = userRoleMapper.getUserRole(userID);
        System.out.println("Roles: " + Roles);
        if(!Roles.contains("1")){
            return new Result("权限不足", "502",
                    new BackstageResult(40003, "permission denied", null));
        }
        return getUserInfo_all();
    }

    @PostMapping("/power")
    Result changePower(@RequestParam("token")String token, @RequestBody BackstagePowerChange backstagePowerChange) {
        System.out.println(backstagePowerChange.toString());
        String user = userTokenMapper.searchUserByToken(token);
        if(user == null)
            return new Result("token无效", "502",
                    new BackstageResult(50008, "token error", null));
        if (backstagePowerChange.isAdd()) {
            return addPower(backstagePowerChange.getEmail(), backstagePowerChange.getPower());
        } else {
            return delPower(backstagePowerChange.getEmail(), backstagePowerChange.getPower());
        }
    }

    Result addPower(String email, String power){
        userRoleMapper.addRole(email, roles.get(power));
        return new Result("OK", "200",
                new BackstageResult(20000, "Add power success", null));
    }
    Result delPower(String email, String power){
        userRoleMapper.delRole(email, roles.get(power));
        return new Result("OK", "200",
                new BackstageResult(20000, "Del power success", null));
    }

    @PostMapping("/delete")
    Result deleteUser(@RequestParam("token") String token, @RequestParam("email") String userID){
        String user = userTokenMapper.searchUserByToken(token);
        if(user == null)
            return new Result("token无效", "502",
                    new BackstageResult(50008, "token error", null));
        UserObject userObject = new UserObject(userID);
        userMapper.Delete(userObject);
        userRoleMapper.deleteUserRole(userID);
        userTokenMapper.deleteUserAllToken(userID);
        System.out.println("delete user: " + userID);
        return new Result("删除用户成功", "200",
                new BackstageResult(20000, "Delete User Success", null));
    }

    @PostMapping("/logout")
    Result logout(String token){
        userTokenMapper.deleteToken(token);
        return new Result("退出登录", "200",
                new BackstageResult(20000, "logout", null));
    }

    Result getUserInfo_all() {
        ArrayList<BackstageUserInfo> backstageUserInfoArrayList = new ArrayList<>();
        ArrayList<String> userArrayList =  userRoleMapper.getAllUser();
        for (String user : userArrayList) {
            ArrayList<String> Roles = userRoleMapper.getUserRole(user);
            ArrayList<String> roleList = new ArrayList<>();
            for (String role : Roles){
                roleList.add(roleMapper.getRoleNameById(role));
            }
            BackstageUser backstageUser = backstageUserMapper.getUserInfo(user);
            BackstageUserInfo backstageUserInfo = new BackstageUserInfo(backstageUser.getId(),
                    roleList, backstageUser.getName(), backstageUser.getAvatar(),
                    backstageUser.getIntroduction());
            backstageUserInfoArrayList.add(backstageUserInfo);
        }
        return new Result(
                "获取成功",
                "200",
                new BackstageResult(20000, "OK",
                        new BackstageUserInfoResult(backstageUserInfoArrayList.size(), backstageUserInfoArrayList)));
    }

}
