package com.chihyao.relaxed.module.messagelist.repository;

import com.chihyao.relaxed.module.messagelist.entity.Message;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID>, DataTablesRepository<Message, UUID> {

}
