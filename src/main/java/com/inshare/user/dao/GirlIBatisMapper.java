package com.inshare.user.dao;

import com.inshare.user.pojo.Girl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
//@Component  // 可以不加此注解，Mybatis会扫描到Mapper能够注入成功
public interface GirlIBatisMapper {

    @Select("select * from girl where age=#{age}")
    List<Girl> getByAge(Integer age);
}
