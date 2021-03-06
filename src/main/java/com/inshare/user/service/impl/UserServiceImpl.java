package com.inshare.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.inshare.user.mapper.SysUserMapper;
import com.inshare.user.entity.SysUser;
import com.inshare.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void saveUser(SysUser user) throws Exception {
        sysUserMapper.insert(user);
    }

    @Override
    @CachePut(cacheNames = "s_user", key = "'_'.concat(#user.id)")
    public void updateUser(SysUser user) {
        sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteUser(String userId) {
        sysUserMapper.deleteByPrimaryKey(userId);
    }

    @Override
    @CachePut(cacheNames = "s_user", key = "'_'.concat(#userId)")
    public SysUser queryUserById(String userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<SysUser> queryUserListPage(String nickName, Integer page, Integer pageSize) {
        //开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(nickName)) {
            criteria.andLike("nickname", "%" + nickName + "%");
        }
        example.orderBy("registTime").desc();

        return sysUserMapper.selectByExample(example);
    }
}
