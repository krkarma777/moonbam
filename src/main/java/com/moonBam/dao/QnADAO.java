package com.moonBam.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.QnADTO;

@Repository
public class QnADAO {

    @Autowired
	SqlSessionTemplate session;

    public int submitQNA(QnADTO qna){
        int num = 0;
        session.insert("QnAmapper.submitQnA");
        return num;
    }
}
