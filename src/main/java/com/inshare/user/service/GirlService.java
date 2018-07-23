package com.inshare.user.service;

import com.inshare.user.entity.Girl;
import com.inshare.user.mapper.GirlMapper;
import com.inshare.user.enums.ResultEnum;
import com.inshare.user.exception.GirlException;
import com.inshare.user.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlMapper girlMapper;

    @Transactional(readOnly = true)
    public void insertTwo(){
        Girl girl1 = new Girl();
        girl1.setName("CaiHua");
        girl1.setAge(28);
        girlRepository.save(girl1);

        Girl girl2 = new Girl();
        girl2.setName("Maxnametolong");
        girl2.setAge(18);
        girlRepository.save(girl2);
    }

    public void getAge(Integer id) throws Exception{
        Optional<Girl> opGirl =  girlRepository.findById(id);
        if (opGirl.isPresent()) {
            Integer age = opGirl.get().getAge();
            if (age < 18) {
                throw new GirlException(ResultEnum.LITTER);
            }else if (age > 60) {
                throw new GirlException(ResultEnum.BIGGER);
            }else {
                throw new GirlException(ResultEnum.AGE_OK);
            }
        }else{
            throw new GirlException(ResultEnum.QUERRY_NO);
        }
    }

    public Girl getGirl(Integer id) {
        Optional<Girl> opGirl =  girlRepository.findById(id);
        if (opGirl.isPresent()) {
            return opGirl.get();
        }
        return null;
    }

    public List<Girl> getGirlByAge(Integer age) {
        return girlMapper.selectAll();
    }
}
