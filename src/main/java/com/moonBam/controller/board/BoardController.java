package com.moonBam.controller.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board/write")
    public String writeForm() {
        return "board/post";
    }
}