package com.inshare.user.service;

import com.inshare.user.repository.GirlRepository;
import com.inshare.user.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

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
}
