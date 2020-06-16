package com.fatrui.accbooks.controller;

import com.fatrui.accbooks.domain.GroupBean;
import com.fatrui.accbooks.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService service;

    @RequestMapping(value = "/getGroup")
    public List<GroupBean> getAllGroup(){
        return service.getAllGroup();
    }
}
