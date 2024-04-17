package com.moonBam.controller.community.oh;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatTableDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatMessagesDAO {

    private final SqlSessionTemplate session;

    public int create(ChatTableDTO requestDTO) {
    	int done= session.insert("ChatMessagesMapper.create", requestDTO);
         return done;
    }
}