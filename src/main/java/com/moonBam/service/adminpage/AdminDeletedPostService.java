package com.moonBam.service.adminpage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moonBam.dao.AdminDeletedPostDAO;
import com.moonBam.dto.AdminDeletedPostDTO;

@Service
public class AdminDeletedPostService {

    @Autowired
    AdminDeletedPostDAO dao;

    public List<AdminDeletedPostDTO> getDeletedPostList(HashMap<String, String> map){
    	System.out.println("3. 서비스 레이어에서 검색 조건 담긴 map 수신");
    	System.out.println("4. DAO로 전달");
        List<AdminDeletedPostDTO> list = dao.getDeletedPostList(map);
        return list;
    }
}
