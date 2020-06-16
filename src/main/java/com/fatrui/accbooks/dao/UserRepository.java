package com.fatrui.accbooks.dao;

import com.fatrui.accbooks.domain.UserBean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBean,Integer> {

    Optional<UserBean> findByUserName(String username);

    List<UserBean> findByAge(byte age);

    List<UserBean> findAll();

    List<UserBean> findByGenderAndAgeAfter(Pageable pageable,String name, byte age);



    int countByUserId(String userid);

    long count();

    List<UserBean> findFirst3ByAgeBetween(byte age,byte age2);

    //HQL
    @Query("select userId,userName,gender,age,registerDate from UserBean where age > ?1 and age< ?2 ")
    //@Query("select ub.userId,ub.userName,ub.gender,ub.age,ub.registerDate from UserBean ub where age > ?1 and age< ?2 ")
    List<UserBean> queryByAge(byte age1,byte age2);


    //原生sql
    @Query(value="select u.user_id,u.user_name,u.gender,u.age,u.register_date from users u where u.user_name like CONCAT(:name,'%')"
            ,nativeQuery=true)
    List<UserBean> queryByUserNameEquals(@Param("name") String name);




}
