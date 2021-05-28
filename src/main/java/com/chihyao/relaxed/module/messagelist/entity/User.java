package com.chihyao.relaxed.module.messagelist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    private String account;
    private String password;
    private String name;
    private String emailAddress;
    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
    private List<String> authorities;

}
