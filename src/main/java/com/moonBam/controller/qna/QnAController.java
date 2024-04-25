package com.moonBam.controller.qna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.moonBam.dto.QnADTO;
import com.moonBam.service.QnAService;


@Controller
public class QnAController {

    @Autowired
    QnAService service;

    //QnA
    @RequestMapping("/qna")
    public ModelAndView getMyQnaList() {
    	System.out.println("qna리스트 가져오기");
        ModelAndView mav = new ModelAndView();
        List<QnADTO> list = new ArrayList<>();
        list = service.getQnaList();
        
        System.out.println("가져온 qna list");
        System.out.println(list);
        mav.addObject("list", list);
        mav.setViewName("qna/qna");
        return mav;
    }
    
    //문의 작성 폼 이동
    @RequestMapping("/newQNA")
    public String newQNA(){
        return "qna/qnaForm";
    }

    //문의 작성 후 저장
    @PostMapping("/submit_qna")
    public String submitQNA(String qna_title, String qna_text){
        
        //작성한 문의 DTO 받아오기
        QnADTO qna = new QnADTO();
        qna.setTitle(qna_title);
        qna.setText(qna_text);
        
//        LocalDate today = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd");
//        String dateString = today.format(formatter);
//        qna.setPostdate(dateString);

        int num =0;
        System.out.println("DB에 저장할 qna");
        System.out.println(qna);
        num = service.submitQNA(qna);
        

        return "redirect:/qna";
    }
}
