package com.moonBam.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.moonBam.dto.AdminDeletedPostDTO;

@Repository
public class AdminDeletedPostDAO {

    @Autowired
    SqlSessionTemplate session;

    public List<AdminDeletedPostDTO> getDeletedPostList(HashMap<String, String> map) {

        List<AdminDeletedPostDTO> list = session.selectList("AdminDeletedPostMapper.getList", map);
        return list;
    }

}
