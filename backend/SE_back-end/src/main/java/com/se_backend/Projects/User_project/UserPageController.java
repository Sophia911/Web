package com.se_backend.Projects.User_project;
import com.se_backend.DB.DB_object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/GetUserInfo")
public class UserPageController {

    @Autowired
    private UserPageMapper userPageMapperapper;

    @PostMapping
    UserPage get_UserInfo_Request(@RequestBody User user){

        //return "i get it";
        UserPage userPage=userPageMapperapper.get_UserInform(user);
        return userPage;
    }


}
