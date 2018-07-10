package com.inshare.user.controller;

import com.inshare.user.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloController {
    @Autowired
    private GirlProperties girlProperties;

    @Value("${content}")
    private String content;

    @GetMapping(value={"/say","/hi"})
    public String say(){
        return content +  "<br/>" + girlProperties.getCupSize();
    }

    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId){
        return "id: " + myId;
    }
}
