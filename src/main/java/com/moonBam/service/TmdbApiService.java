package com.moonBam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

	/*
	 *{ "id": 878, "name": "Science Fiction" }, { "id":
	 * 10770, "name": "TV Movie" }, { "id": 53, "name": "Thriller" }, { "id": 10752,
	 * "name": "War" }, { "id": 37, "name": "Western" } ] }
	 */
	public int getAndSaveMovies(int limitPage) throws Exception {
		
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
		
		// 임시 대참사 제한 : 500
		if (limitPage>500) {
			limitPage=500;
		}
		int insertedPage = 0;
		
		// limitPage까지 한페이지씩 api요청 -> results 한행씩 insert 
		for(int page=1;page<=limitPage;page++) {
			
			// 1. MovieResponseDto getMoviePage(page)
			MovieResponseDTO movieResponse = dao.getMoviePage(page);
			
			// movie 데이터들이 담겨있는 list 순회하며 한행씩 insert
			List<MovieResultResponseDTO> movies = movieResponse.getResults();
			
			for(int i=0;i<movies.size();i++) {
				// 2. num insertMovie(dto)
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
				int num = dao.insertMovie(movies.get(i));
			}
			insertedPage ++;
			
			// api 초당 50회 제한 => 초당 50회의 요청이 가능
			// 그 이상 속도로 요청하면 429 error
			if(insertedPage%50==0) {
				Thread.sleep(1000);
			}
			if(insertedPage%10==0) {
				System.out.println("현재 진행 페이지: "+ insertedPage);
			}
			
		}

		return insertedPage;
	}

	public List<CreditDTO> getCredits(String contId) {
		return dao.getCredits(contId);
	}
}
