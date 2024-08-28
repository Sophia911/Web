package com.se_backend.Projects.User;

import com.alibaba.fastjson.JSONObject;
import com.se_backend.DB.DB_Mapper.UserRoleMapper;
import com.se_backend.DB.DB_Mapper.UserTokenMapper;
import com.se_backend.DB.DB_object.UserToken;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    private String []nameArray = new String[]{"Alice", "Bob", "Carter", "Dickens", "Ellery", "Frederick", "Green", "Harris"};
    @GetMapping("/all")// 查询全部用户
    Result getAllUsers(){
        ArrayList<UserObject> all_users = userMapper.selectAll();
        int num = all_users.size();
        return new Result("total: " + num, "200", all_users);
    }
    @PostMapping("/search")// 查询是否存在指定id的用户
    Result getUserById(@RequestBody UserObject userObject){
        String Being = userMapper.selectByEmail(userObject.getId());
        System.out.println( "Post user data: " + Being);
        String userMsg = (Being == null) ? "no such user" : "the user";
        String statusCode = (Being == null) ? "404" : "200";
        return new Result(userMsg, statusCode, Being);
    }
    @PostMapping("/check")// 邮箱密码确认
    Result checkUserInfo(@RequestBody UserObject userObject){
        String password = userMapper.getPasswordByEmail(userObject.getId());
        boolean OK = password.equals(userObject.getPassword());
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();
        if(OK){
            UserToken userToken=new UserToken(userObject.getId(),uuid);
            if(userTokenMapper.checkAccount(userToken) ==null){
                userTokenMapper.SaveToken(userToken);
            }else{
                userTokenMapper.UpdateToken(userToken);
            }
        }
        return new Result("check password", OK ? "200" : "401", token);
    }
    @PostMapping("/insert")//注册用户
    Result addUser(@RequestBody UserObject userObject){
        System.out.println("注册新用户：" + userObject.getId());
        userRoleMapper.addVisitorRole(userObject);
        Random random = new Random();
        int avatarNumber = random.nextInt(10) + 1;
        int introNumber = random.nextInt(8) + 1;
        userObject.setAvatar("https://www.ichlearning.cn:8080/UserAvatar/ava" + avatarNumber + ".jpg");
        userObject.setBackground("level" + avatarNumber);
        userObject.setName(nameArray[random.nextInt(nameArray.length)]);
        userObject.setIntroduction(introNumber);
        userMapper.Insert(userObject);
        return new Result("register new user","200", null);
    }
    @PostMapping("/update")//更新
    Result updateUser(@RequestBody UserObject userObject){
        return new Result("","200", userMapper.Update(userObject));
    }
    @PostMapping("/delete")//删除
    Result deleteUser(@RequestBody UserObject userObject){
        return new Result("","", userMapper.Delete(userObject));
    }
    @PostMapping("/admin/login")// 测试：返回token
    Result getToken(@RequestBody UserObject userObject){
        String password = userMapper.getPasswordByEmail(userObject.getId());
        boolean OK = password.equals(userObject.getPassword());
        JSONObject object = new JSONObject();
        object.put("token", "admin-token");
        System.out.println(object);
        return new Result("check password", OK ? "200" : "401", object);
    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in User");
        System.out.println(ex.toString());
        return new Result("error","400",null) ;
    }
}
