package com.inshare.user.service;

import com.inshare.user.entity.SysUser;

import java.util.List;

public interface UserService {

    public void saveUser(SysUser user) throws Exception;

    public void updateUser(SysUser user);

    public void deleteUser(String userId);

    public SysUser queryUserById(String userId);

    public List<SysUser> queryUserListPage(String nickName, Integer page, Integer pageSize);
}