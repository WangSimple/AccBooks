package com.fatrui.accbooks.dao;

import com.fatrui.accbooks.BaseTest;
import com.fatrui.accbooks.domain.GroupBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;
import java.util.List;



public class GroupRepositoryTest extends BaseTest{
    @Autowired
    private GroupRepository repository;
    @Test
    @Transactional
    public void test1(){
        List<GroupBean> list=repository.findAll();
        System.out.println(list);
        Assert.assertSame(2,list.size());
    }
}