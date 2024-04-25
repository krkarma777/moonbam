package com.moonBam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.moonBam.dao.TmdbApiDAO;
import com.moonBam.dto.CreditDTO;
import com.moonBam.dto.MovieResponseDTO;
import com.moonBam.dto.MovieResultResponseDTO;


@Service
public class TmdbApiService {

	@Autowired
	TmdbApiDAO dao;
	
	Map<Integer, String> genresMap = new HashMap<Integer, String>();

	// 매일 영화데이터 업데이트
    // 매일 오전 5시에 실행 (초, 분, 시, 일, 월, 요일)
    @Scheduled(cron = "0 0 5 * * ?")
    public void updateMovieData() throws Exception {
    	getAndSaveMovies(500);
    }
    
    // 영화데이터 저장 로직
	public int getAndSaveMovies(int limitPage) throws Exception {
		
    	// TMDB에서 받아온 장르id 매핑 데이터
		genresMap.put(28, "Action");
		genresMap.put(12, "Adventure");
		genresMap.put(16, "Animation");
		genresMap.put(35, "Comedy");
		genresMap.put(80, "Crime");
		genresMap.put(99, "Documentary");
		genresMap.put(18, "Drama");
		genresMap.put(10751, "Family");
		genresMap.put(14, "Fantasy");
		genresMap.put(36, "History");
		genresMap.put(27, "Horror");
		genresMap.put(10402, "Music");
		genresMap.put(9648, "Mystery");
		genresMap.put(10749, "Romance");
		genresMap.put(878, "Science Fiction");
		genresMap.put(10770, "Movie");
		genresMap.put(53, "Thriller");
		genresMap.put(10752, "War");
		genresMap.put(37, "Western");
		
		// 최소, 최대치 설정
		if(limitPage<10) {
			limitPage = 10;
		} else if (limitPage>500) {
			limitPage=500;
		}
		
		int insertedPage = 0;
		// limitPage까지 한페이지씩 api요청 -> results 한행씩 insert 
		for(int page=1;page<=limitPage;page++) {
			
			// 1. TMDB에 API 요청 -> 영화데이터 반환받음
			MovieResponseDTO movieResponse = dao.getMoviePage(page);
			
			// 2. Movie 리스트 순회. DB에 하나의 행씩 삽입
			List<MovieResultResponseDTO> movies = movieResponse.getResults();
			for(int i=0;i<movies.size();i++) {
				
				// 장르ID값 -> 장르로 치환하는 구문
				List<Integer> genre_ids =  movies.get(i).getGenre_ids();
				String genres = "";
				for (Integer genre_id : genre_ids) {
					if(genres.equals(""))
						genres+=genresMap.get(genre_id);
					else
						genres+=","+genresMap.get(genre_id);
					System.out.println("genres: "+genres);
				}
				movies.get(i).setGenres(genres);
				System.out.println(movies.get(i).getGenres());
				
				// DB에 데이터 삽입
				int num = dao.insertMovie(movies.get(i));
			}
			insertedPage ++;
			
			// TMDB API는 초당 50회 제한이 존재함
			// 그 이상 속도로 요청하면 429 error 발생
			// 50회 요청마다 1초 강제 딜레이 기능
			if(insertedPage%50==0) {
				Thread.sleep(1000);
			}
			// 진행상황 10Page 단위로 출력
			if(insertedPage%10==0) {
				System.out.println("\n현재 진행 페이지: "+ insertedPage);
			}
		}

		// 저장된 페이지수 반환
		return insertedPage;
	}

	// 배우 정보 전달
	public List<CreditDTO> getCredits(String contId) {
		return dao.getCredits(contId);
	}
}
