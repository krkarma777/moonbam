package com.moonBam.controller.board.beforeRefactor;


import com.moonBam.dto.ContentDTO;
import com.moonBam.dto.board.PageDTO;
import com.moonBam.dto.board.PostPageDTO;
import com.moonBam.service.MainService;
import com.moonBam.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// 게시판 뷰 컨트롤러 구현
@Controller
public class BoardViewController {
    
    private String postBoard; // 게시판 이름
	
	@Autowired
    PostService service;
	@Autowired
    MainService mService;
	
	@GetMapping("/board/{postBoard}")
	public String postBoard(@PathVariable("postBoard") String postBoard,
                            @RequestParam Map<String, String> paramMap,
                            Model model) {
        
        // 현재 페이지 번호 설정
        String curPageStr = paramMap.get("curPage");
        
        int curPage = 1; // 기본값
        if (curPageStr != null && !curPageStr.isEmpty()) {
            curPage = Integer.parseInt(curPageStr);
        }
        
        String postCategoryId = paramMap.get("pc"); //get?pc=
        
        // 페이지당 게시글 수 설정

        int perPage = 17;

        int offset = (curPage - 1) * perPage;
        
        // 요청에 따른 매개변수 맵 설정
        HashMap<String, Object> map = new HashMap<>();
        map.put("postBoard", postBoard);
        map.put("offset", offset);
        map.put("perPage", perPage);
        map.put("curPage", curPage);
        map.put("postCategoryId", postCategoryId);
        
        // 검색 조건 추가
        searchBoard(paramMap, map);
        
        // 정렬 추가
        sortIndex(paramMap, map);
        
        // 페이지 정보 가져오기
        PageDTO<PostPageDTO> pageDTO = service.getPostsByPage(map);
        
		List<PostPageDTO> hotList = service.selectAll(new HashMap<String, String>() {{
		    put("board", postBoard);
		    put("postCount", "5");
		}});
		
		List<PostPageDTO> popularListAll = service.popularPostTwoDays(new HashMap<String, String>() {{
		    put("postCount", "10");
		}});
		
		final String category = postBoard.contains("movie") ? "movie" : 
            postBoard.contains("tv") ? "tv" : 
            postBoard.contains("book") ? "book" : "";

		
		List<PostPageDTO> popularListCategory = service.popularPostTwoDays(new HashMap<String, String>() {{
			put("board", category);
			put("board2", category+"Info");
			put("board3", category+"Meet");
			put("postCount", "10");
		}});

        // 모델에 페이지 정보와 게시판 이름 추가
		model.addAttribute("pDTO", pageDTO);
		model.addAttribute("postBoard", postBoard);
		model.addAttribute("hotList", hotList);
		model.addAttribute("popularListCategory", popularListCategory);
		model.addAttribute("popularListAll", popularListAll);
		
		List<ContentDTO> movieTopList = mService.selectTop();
        model.addAttribute("movieTopList", movieTopList);
        
        String [] genreList = {"Drama", "Comedy", "Thriller"};
        
        Random random = new Random();
        
        String genre = genreList[random.nextInt(3)];
        
        List<ContentDTO> genreMovieTopList = mService.selectGenreTop(genre);
        model.addAttribute("genreMovieTopList", genreMovieTopList);
        model.addAttribute("genre", genre);
        
        
		return "board/boardView";
	}//
	

    // 검색 기능을 처리하는 메소드
    private void searchBoard(Map<String, String> paramMap, HashMap<String, Object> map) {
        // 검색 위치와 검색어 가져오기
        String searchPosition = paramMap.get("selectSearchPositionText"); // get
        String searchText = paramMap.get("inputSearchFreeText"); // get

        // 검색 위치와 검색어가 있을 경우에만 검색 조건 추가
        if (searchPosition != null && searchText != null) {
            switch (searchPosition) {
                case "titleText": {
                    map.put("postTitle", searchText.trim());
                    map.put("postText", searchText.trim());
                    break;
                }
                case "postTitle": {
                    map.put("postTitle", searchText.trim());
                    break;
                }
                case "postText": {
                    map.put("postText", searchText.trim());
                    break;
                }
                case "userId": {
                    map.put("userId", searchText.trim());
                    break;
                }
            }
        }
    }//
    
    
    private void sortIndex(Map<String, String> paramMap, HashMap<String, Object> map) {
    	String orderType = paramMap.get("sortIndex"); //get
    	if (orderType != null) {
    		switch (orderType) {
    		case "likeNum": {
    			map.put("sortIndex", "likeNum");
    			break;
    		}
    		case "viewNum": {
    			map.put("sortIndex", "viewNum");
    			break;
    		}
    		}
    	}
    }//
}
