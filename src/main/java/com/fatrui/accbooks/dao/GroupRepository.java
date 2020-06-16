package com.fatrui.accbooks.dao;

import com.fatrui.accbooks.domain.GroupBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupBean,Integer> {
    List<GroupBean> findAll();
}
