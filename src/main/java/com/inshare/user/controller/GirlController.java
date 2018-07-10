package com.inshare.user.controller;

import com.inshare.user.domain.Girl;
import com.inshare.user.repository.GirlRepository;
import com.inshare.user.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository grilRepository;

    @Autowired
    private GirlService girlService;

    //查询所有
    @GetMapping(value = "/girl")
    public List<Girl> girlList() {
        return grilRepository.findAll();
    }

    //根据id查询
    @GetMapping(value = "/girl/{id}")
    public Girl girlOne(@PathVariable("id") Integer id) {
        Optional<Girl> opGirl =  grilRepository.findById(id);
        if (opGirl.isPresent()) {
            return opGirl.get();
        }
        return null;
    }

    //名称模糊查询
    @PostMapping(value = "/girl")
    public List<Girl> girlListByName(@RequestParam("name") String name) {
        return grilRepository.findByNameContaining(name);
    }

    //添加(@Valid验证)
    @PostMapping(value = "/addgirl")
    public Girl addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        girl.setName(girl.getName());
        girl.setAge(girl.getAge());
        return grilRepository.save(girl);
    }

    //更新
    @PutMapping(value = "/girl/{id}")
    public Girl updateGirl(@PathVariable("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setName(name);
        girl.setAge(age);
        return grilRepository.save(girl);
    }

    //根据id删除
    @DeleteMapping(value = "/girl/{id}")
    public void deleteGirl(@PathVariable("id") Integer id){
        grilRepository.deleteById(id);
    }

    @PostMapping(value = "/girl/two")
    public void addTwoGirl(){
        girlService.insertTwo();
    }
}
