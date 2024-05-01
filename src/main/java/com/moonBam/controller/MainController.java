package com.moonBam.controller;

import com.moonBam.controller.adminpage.AdminCounter;
import com.moonBam.controller.content.KoficAPI;
import com.moonBam.dto.ChatRoomDTO;
import com.moonBam.dto.CommunityPageDTO;
import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.service.ChatRoomService;
import com.moonBam.service.CommunityHomeService;
import com.moonBam.service.MainService;
import com.moonBam.service.PostService;
import com.moonBam.service.adminpage.announcement.AnnouncementService;

import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class MainController {

    @Autowired
    PostService service;
    @Autowired
    MainService mService;
    @Autowired
    CommunityHomeService cService;
    
    @Autowired
    KoficAPI kofic;
    @Autowired
    ChatRoomService crService;
    // pupop
    @Autowired
    AnnouncementService annoService;
    @Autowired
    AdminCounter counter;
    @GetMapping("/")
    public String mainView(Model model, @RequestParam(value = "cg", required = false) String category, Principal principal,
    						HttpSession session, String genre) {
        System.out.println("principal = " + principal);

        counter.init();//접속자 +1

        String nextPage = "main";

        List<PostPageDTO> moviePostList = service.selectAll(new HashMap<String, String>() {
            {
                put("board", "movie");
                put("postCount", "14");
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
      
        // popup get popup num list
        String cate = (category == null) ? "main" : category;
        List<Integer> list = annoService.popupNnumList(new HashMap<String, String>() {
            {
                put("popup", "on");
                put("category", cate);
            }
        });
        	
        model.addAttribute("movieList", moviePostList);
        model.addAttribute("movieMeetList", movieMeetList);
        model.addAttribute("movieInfoList", movieInfoList);
      
        // popup
        model.addAttribute("list", list);
        List<String> categoryList = new ArrayList<>();;
        
        if (category != null) {
            switch (category) {
            case "movie":
	    		List<ContentDTO> movieTopList = mService.selectTop();
                model.addAttribute("movieTopList", movieTopList);

                //장르 인기순 가져오기
                //처음엔 드라마로.
                if(genre==null) {
                	genre = "Drama";
                }
                List<ContentDTO> genreMovieTopList = mService.selectGenreTop(genre);
                model.addAttribute("genreMovieTopList", genreMovieTopList);
                model.addAttribute("genre", genre);

	    		model.addAttribute("category", category);
                categoryList.add("전체");
                categoryList.add("드라마");
                categoryList.add("코미디");
                categoryList.add("스릴러");
	    		model.addAttribute("categoryList", categoryList);
                nextPage = "movie/movieHome";
                break;
            case "community":
            	String curPage = "1";
            	String searchCategory = "";
            	String searchValue = "";
            	CommunityPageDTO cpDTO= cService.chatRoomList(searchCategory, searchValue, curPage);
            	model.addAttribute("cpDTO", cpDTO);
                List<ChatRoomDTO> chatRoomMapList = crService.getAllChatRooms();
                if (chatRoomMapList != null && !chatRoomMapList.isEmpty()) {
                    // chatRoomMapList가 null이 아니고 비어 있지 않은 경우에만 모델에 추가
                    model.addAttribute("chatRoomMapList", chatRoomMapList);
                }
            	model.addAttribute("category", category);
            	categoryList.add("전체");
	    		categoryList.add("영화");
	    		categoryList.add("독서");
	    		categoryList.add("음악");
	    		model.addAttribute("categoryList", categoryList);
	    		
	    		String communityCategory = "communitySearch";
	    		model.addAttribute("communityCategory", communityCategory);
	    		
                nextPage = "community/communityHome";
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