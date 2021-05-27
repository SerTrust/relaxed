package com.chihyao.relaxed.module.messagelist.repository;

import com.chihyao.relaxed.module.messagelist.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Attachment, UUID>{

}
