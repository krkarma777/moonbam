package com.moonBam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.QnADAO;
import com.moonBam.dto.QnADTO;

@Service
public class QnAService {

    @Autowired
    QnADAO dao;

    public int submitQNA(QnADTO qna){
        int num = dao.submitQNA(qna);
        return num;
    }

	public List<QnADTO> getQnaList() {
		List<QnADTO>list = dao.getQnaList();
		return list;
	}

    
}
