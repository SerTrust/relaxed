package com.chihyao.relaxed.module.messagelist.controller.request.ui;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
public class MessageVO {

    private UUID id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    private List<MultipartFile> files;

}
