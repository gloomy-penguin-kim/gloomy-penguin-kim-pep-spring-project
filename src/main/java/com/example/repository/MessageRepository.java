package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
 
import com.example.entity.Message;

import java.util.List; 

public interface MessageRepository extends JpaRepository<Message, Integer>  { 
    List<Message> findAllByPostedBy(@Param("accountId") Integer accountId);
}
