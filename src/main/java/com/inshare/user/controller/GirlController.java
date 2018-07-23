package com.inshare.user.controller;

import com.inshare.user.entity.Result;
import com.inshare.user.entity.Girl;
import com.inshare.user.repository.GirlRepository;
import com.inshare.user.service.GirlService;
import com.inshare.user.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    //查询所有
    @GetMapping(value = "/girl")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }

    //根据id查询
    @GetMapping(value = "/girl/{id}")
    public Girl girlOne(@PathVariable("id") Integer id) {
        Optional<Girl> opGirl =  girlRepository.findById(id);
        if (opGirl.isPresent()) {
            return opGirl.get();
        }
        return null;
    }

    //名称模糊查询
    @PostMapping(value = "/girl")
    public List<Girl> girlListByName(@RequestParam("name") String name) {
        return girlRepository.findByNameContaining(name);
    }

    //添加(@Valid验证)
    @PostMapping(value = "/addgirl")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResultUtil.error(-1, bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setName(girl.getName());
        girl.setAge(girl.getAge());
        girl.setPassword(girl.getPassword());
        girl.setCreateTime(new Date());

        return ResultUtil.success(girlRepository.save(girl));
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
        return girlRepository.save(girl);
    }

    //根据id删除
    @DeleteMapping(value = "/girl/{id}")
    public void deleteGirl(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }

    @PostMapping(value = "/girl/two")
    public void addTwoGirl(){
        girlService.insertTwo();
    }

    @GetMapping(value = "/girl/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        girlService.getAge(id);
    }

    @GetMapping(value = "/girl/age/{age}")
    public List<Girl> getByAge(@PathVariable("age") Integer age) {
        return girlService.getGirlByAge(age);
    }
}
