package com.fatrui.accbooks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString(callSuper = true,includeFieldNames = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserBean implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "seq_users_id")
    private int userId;
    private String userName;
    private String gender;
    private byte age;
    private Date registerDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="GROUP_ID")
    @Fetch(FetchMode.JOIN)
    private GroupBean groupBean;



}
