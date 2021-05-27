package com.chihyao.relaxed.module.messagelist.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    private LocalDate date;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Attachment> attachments;

}
