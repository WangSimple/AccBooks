package com.fatrui.accbooks.service;

import com.fatrui.accbooks.dao.GroupRepository;
import com.fatrui.accbooks.domain.GroupBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository repository;

    public List<GroupBean> getAllGroup(){
        return repository.findAll();
    }
}
