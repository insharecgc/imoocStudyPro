package com.inshare.user.controller;

import com.inshare.HelloService;
import com.inshare.user.entity.Result;
import com.inshare.user.properties.GirlProperties;
import com.inshare.user.properties.ResourceProperties;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
    public Result say(HttpServletResponse response){
        // 服务器端 CORS 来实现跨域
//        response.addHeader("Access-Control-Allow-Origin","http://192.168.1.106:8089");
//        response.addHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers","Content-Type");
//        response.addHeader("Access-Control-Allow-Credentials","true");

        return ResultUtil.success();
//        return content +  "--" + girlProperties.getCupSize();
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
}
