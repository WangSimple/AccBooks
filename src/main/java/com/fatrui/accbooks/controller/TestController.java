package com.fatrui.accbooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TestController  {

    @RequestMapping("/hello")
    public String index(){

        return "Hello,world!";
    }


}
