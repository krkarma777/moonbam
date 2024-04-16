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
        List<AdminDeletedPostDTO> list = dao.getDeletedPostList(map);
        return list;
    }
}
