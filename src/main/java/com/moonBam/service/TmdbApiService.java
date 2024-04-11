package com.moonBam.service;

import java.util.List;

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
	
	public int getAndSaveMovies(int limitPage) throws Exception {
		
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
