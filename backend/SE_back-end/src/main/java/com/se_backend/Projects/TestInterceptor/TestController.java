package com.se_backend.Projects.TestInterceptor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Test")
public class TestController {
    @GetMapping
    String TestString(){
        return "adas";
    }
    @PostMapping
    String LoginCheck(@RequestBody Test test){
        System.out.println("/Test");
        System.out.println(test.getUserPowerCheck().getId());
        System.out.println(test.getUserPowerCheck().getToken());
        return "True";
    }
    @PostMapping("/2")
    String LoginCheck2(@RequestBody Test test){
        System.out.println("/Test/2");
        System.out.println(test.getUserPowerCheck().getId());
        System.out.println(test.getUserPowerCheck().getToken());
        return "True2";
    }
}
