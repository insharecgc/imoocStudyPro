package com.inshare.user.dao;

import com.inshare.user.pojo.Girl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GirlIBatisMapper {

    @Select("select * from girl where age=#{age}")
    public List<Girl> getByAge(Integer age);
}
