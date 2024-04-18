package com.moonBam.controller.community.chat;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.ChatTableDTO;
import com.moonBam.dto.CommunityBadWordsDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChatMessagesDAO {

    private final SqlSessionTemplate session;

    public int insert(ChatTableDTO requestDTO) {
    	int done= session.insert("ChatMessagesMapper.create", requestDTO);
         return done;
    }

	public List<String> badWordsSelectAll() {
		
		return session.selectList("ChatMessagesMapper.badWordsSelectAll");
	}
    
    
    
}