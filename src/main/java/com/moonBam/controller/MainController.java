package com.moonBam.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.service.MainService;
import com.moonBam.service.PostService;
import com.moonBam.service.adminpage.announcement.AnnouncementService;

@Controller
public class MainController {

    @Autowired
    PostService service;
    @Autowired
    MainService mService;

    // pupop
    @Autowired
    AnnouncementService annoService;
    
    @GetMapping("/")
    public String mainView(Model model, @RequestParam(value = "cg", required = false) String category) {
        String nextPage = "main";

        List<PostPageDTO> moviePostList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movie");
                put("postCount", "5");
            }
        });

        List<PostPageDTO> movieMeetList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movieMeet");
                put("postCount", "5");
            }
        });

        List<PostPageDTO> movieInfoList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movieInfo");
                put("postCount", "5");
            }
        });

        // popup
        List<Integer> list = annoService.popupNnumList("popup");
      	
        model.addAttribute("movieList", moviePostList);
        model.addAttribute("movieMeetList", movieMeetList);
        model.addAttribute("movieInfoList", movieInfoList);
        // popup
        model.addAttribute("list", list);

        if (category != null) {
            switch (category) {
            case "movie":
            	//영화 가져오기(인기 순은 아직)
	    		List<ContentDTO> movieTopList = mService.selectTop();
	    		model.addAttribute("movieTopList", movieTopList);
                nextPage = "movieHome";
                break;
            case "community":
                nextPage = "communityHome";
                break;
            case "tv":
                nextPage = "TvHome";
                break;
            default:
                nextPage = "main";
                break;
            }
        }
        return nextPage;
    }

}