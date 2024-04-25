package com.moonBam.dao;

import java.util.List;

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
        System.out.println(qna);
        session.insert("QnAmapper.submitQnA");
        return num;
    }

	public List<QnADTO> getQnaList() {
		List<QnADTO> list = session.selectList("QnAMapper.getQnaList");
		return list;
	}
}
