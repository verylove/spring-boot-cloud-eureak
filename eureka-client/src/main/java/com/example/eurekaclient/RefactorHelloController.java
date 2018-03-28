package com.example.eurekaclient;

import com.example.helloserviceapi.service.HelloService;
import com.example.helloserviceapi.dto.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RefactorHelloController implements HelloService{
    @Override
    public String hello(@RequestParam("name") String name){
        return "Ref Hello "+ name;
    }

    @Override
    public User hello(@RequestHeader("name") String  name, @RequestHeader("age") Integer age){
        return new User(name,age);
    }


    @Override
    public String hello(@RequestBody User user) {
        return "Ref Hello "+ user.getName()+ "," + user.getAge();
    }


}