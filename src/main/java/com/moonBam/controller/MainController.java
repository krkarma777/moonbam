package com.moonBam.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.CommunityPageDTO;
import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.service.CommunityHomeService;
import com.moonBam.service.MainService;
import com.moonBam.service.PostService;
import com.moonBam.service.adminpage.announcement.AnnouncementService;

@Controller
public class MainController {

    @Autowired
    PostService service;
    @Autowired
    MainService mService;
    @Autowired
    CommunityHomeService cService;

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
        
        List<String> categoryList = new ArrayList<>();;
        
        if (category != null) {
            switch (category) {
<<<<<<< HEAD
                case "movie":
                    //영화 가져오기(인기 순은 아직)
                    List<ContentDTO> movieTopList = mService.selectTop();
                    model.addAttribute("movieTopList", movieTopList);
                    nextPage = "movieHome";
                    break;
                case "book":
                    nextPage = "BookHome";
                    break;
                case "tv":
                    nextPage = "TvHome";
                    break;
                default:
                    nextPage = "main";
                    break;
=======
            case "movie":
	    		List<ContentDTO> movieTopList = mService.selectTop();
	    		model.addAttribute("movieTopList", movieTopList);
	    		model.addAttribute("category", category);
	    		categoryList.add("한국영화");
	    		categoryList.add("해외영화");
	    		model.addAttribute("categoryList", categoryList);
                nextPage = "movieHome";
                break;
            case "community":
            	String curPage = "1";
            	String searchCategory = "";
            	String searchValue = "";
            	CommunityPageDTO cpDTO= cService.chatRoomList(searchCategory, searchValue, curPage);
            	model.addAttribute("cpDTO", cpDTO);
            	
            	model.addAttribute("category", category);
	    		categoryList.add("영화");
	    		categoryList.add("독서");
	    		categoryList.add("음악");
	    		model.addAttribute("categoryList", categoryList);
                nextPage = "community/communityHome";
                break;
            case "tv":
                nextPage = "TvHome";
                break;
            default:
                nextPage = "main";
                break;
>>>>>>> branch 'community' of https://github.com/krkarma777/moonbam
            }
        }
        return nextPage;
    }
}