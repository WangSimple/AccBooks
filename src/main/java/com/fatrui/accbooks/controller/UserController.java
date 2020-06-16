package com.fatrui.accbooks.controller;

import com.fatrui.accbooks.domain.UserBean;
import com.fatrui.accbooks.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //http://localhost:8081/findAll
    @RequestMapping("findAll")
    public List<UserBean> findAll(){
        return userRepository.findAll();
    }
    //http://localhost:8081/getUser/44
    @RequestMapping("/getUser/{id}")
    public Optional<UserBean> getUser(@PathVariable int id){
        return userRepository.findById(id);
    }

    //http://localhost:8081/getUserByAge/20
    @RequestMapping("/getUserByAge/{age}")
    public List<UserBean> getUserByAge(@PathVariable byte age){
            return userRepository.findByAge(age);
    }

    //http://localhost:8081/getUsers/男/19
    @RequestMapping("/getUsers/{gender}/{age}")
    public List<UserBean> getUsers(@PathVariable String gender,@PathVariable byte age){
        PageRequest page=new PageRequest(1,2);
        return userRepository.findByGenderAndAgeAfter(page,gender,age);
    }

    //http://localhost:8081/getUserByName/%E5%BC%A0%E4%B8%89
    @RequestMapping("/getUserByName/{name}")
    public Optional<UserBean> getUserByName(@PathVariable String name){
        return userRepository.findByUserName(name);
    }

    //http://localhost:8081/getCount
    @RequestMapping("/getCount")
    public Object getCount() {
        return (Object)userRepository.count();
    }

    @RequestMapping("/getUserIdCount/{id}")
    public Object getUserIdCount(@PathVariable String id){
        return userRepository.countByUserId(id);
    }

    //http://localhost:8081/getAgeBetween/1/30
    @RequestMapping("/getAgeBetween/{age1}/{age2}")
    public List<UserBean> getFirst3AgeBetween(@PathVariable byte age1,@PathVariable byte age2){
        return userRepository.findFirst3ByAgeBetween(age1,age2);
    }

    @RequestMapping("/queryAgeBetween/{age1}/{age2}")
    public List<UserBean> queryAgeBetween(@PathVariable byte age1,@PathVariable byte age2){
        System.out.println(age1);
        System.out.println(age2);
        System.out.println(userRepository);
        return userRepository.queryByAge(age1,age2);
    }

    @RequestMapping("/queryNameLike/{name}")
    public List<UserBean> queryNameLike(@PathVariable String name){
        return userRepository.queryByUserNameEquals(name);
    }

    //http://localhost:8081/saveUsers
    //[{"userName" : "bbb","gender" : "男","age" : 20}, {"userName" : "ccc","gender" : "男","age" : 20}]
    //[{"userId": 1001,"userName" : "bbb","gender" : "男","age" : 20}, {"userId": 1002,"userName" : "ccc","gender" : "男","age" : 20}]
    //该方法会insert多次，不推荐用
    @RequestMapping("/saveUsers")
    public List<UserBean> saveUser(@RequestBody List<UserBean> users){
        for (UserBean user:users) {
            System.out.println(user.toString());
        }
        return userRepository.saveAll(users);
    }
}


