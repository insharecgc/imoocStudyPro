package com.inshare.user.controller;

import com.inshare.user.entity.Result;
import com.inshare.user.entity.SysUser;
import com.inshare.user.service.UserService;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "user")
public class SysUserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @PostMapping(value = "/save")
    public void saveUser(SysUser user) throws Exception {
        user.setRegistTime(new Date());
        userService.saveUser(user);
    }

    @GetMapping(value = "/get/{id}")
    public SysUser getAllUser(@PathVariable("id")String id){
        return userService.queryUserById(id);
    }

    @PostMapping(value = "/get/{page}")
    public Result queryUserListByNikeName(@PathVariable("page")Integer page,
                                          @RequestParam("nickName") String nickName) {
        if (page == null) {
            page = 1;
        }

        int pageSize=3;

        List<SysUser> userList = userService.queryUserListPage(nickName, page, pageSize);

        return ResultUtil.success(userList);
    }
}
