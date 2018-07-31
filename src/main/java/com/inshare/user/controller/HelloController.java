package com.inshare.user.controller;

import com.inshare.user.properties.GirlProperties;
import com.inshare.user.properties.ResourceProperties;
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

    @Autowired
    private ResourceProperties resourceProperties;

    @Autowired
    private HelloService helloService;

    @Value("${content}")
    private String content;

    @GetMapping(value={"/say","/hi"})
    public String say(){
        return content +  "<br/>" + girlProperties.getCupSize();
    }

    @GetMapping(value="/testAutoConf")
    public String testAutoConfig(){
        return helloService.sayHello();
    }

    @GetMapping(value = "/resource")
    public String resource(){
        return resourceProperties.getName()+" -- "
                + resourceProperties.getLanguage() + " -- "
                + resourceProperties.getAge();
    }

    @GetMapping(value = "/test")
    public String test(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId){
        return "id: " + myId;
    }

    @GetMapping(value="/sayHi")
    public String sayHi(){
        return helloService.sayHi();
    }
}
