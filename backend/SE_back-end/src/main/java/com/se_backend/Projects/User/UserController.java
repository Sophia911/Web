package com.se_backend.Projects.User;

//import com.alibaba.fastjson.JSONObject;
import com.se_backend.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
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
        return new Result("check password", OK ? "200" : "401", OK);
    }
    @PostMapping("/insert")//注册用户
    Result addUser(@RequestBody UserObject userObject){
        return new Result("register user","200", userMapper.Insert(userObject));
    }
    @PostMapping("/update")//更新
    Result updateUser(@RequestBody UserObject userObject){
        return new Result("","200", userMapper.Update(userObject));
    }
    @PostMapping("/delete")//删除
    Result deleteUser(@RequestBody UserObject userObject){
        return new Result("","", userMapper.Delete(userObject));
    }
//    @PostMapping("/admin/login")// 测试：返回token
//    Result getToken(@RequestBody UserObject userObject){
//        String password = userMapper.getPasswordByEmail(userObject.getId());
//        boolean OK = password.equals(userObject.getPassword());
//        JSONObject object = new JSONObject();
//        object.put("token", "admin-token");
//        System.out.println(object);
//        return new Result("check password", OK ? "200" : "401", object);
//    }
    @ExceptionHandler(Exception.class)
    public Result catchError(Exception ex){
        System.out.println("get error in User");
        System.out.println(ex.toString());
        return new Result("error","400",null) ;
    }
}
