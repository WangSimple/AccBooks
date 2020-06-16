package com.fatrui.accbooks.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString(callSuper = true,includeFieldNames = true)
@Entity
@Table(name="USERGROUP")
public class GroupBean {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_group_id")
    private String groupId;
    private String groupName;
    //@JoinColumn(name="USER_ID")
   /* @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "groupBean")
    private List<UserBean> userb=new ArrayList<UserBean>();*/
}
