package com.moonBam.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.moonBam.dto.CreditDTO;
import com.moonBam.dto.CreditResponseDTO;
import com.moonBam.dto.MovieResponseDTO;
import com.moonBam.dto.MovieResultResponseDTO;


@Repository
public class TmdbApiDAO {
	
	@Value("${tmdb.key}")
	private String key;
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	SqlSessionTemplate session;

	// tmdb에 api 요청 20개의 영화 데이터가 1페이지 단위로 반환됨
	public MovieResponseDTO getMoviePage(int page) {
    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko-KR&sort_by=popularity.des&page="+page;
    	MovieResponseDTO movieResponse = restTemplate.getForObject(apiUrl, MovieResponseDTO.class);
    	//System.out.println(movieResponse);
    	//System.out.println("0번째값 테스트: "+ movieResponse.getResults().get(0));
        return movieResponse;
	}

	public int insertMovie(MovieResultResponseDTO movieResultResponseDto) {
		int num = session.insert("insertMovie", movieResultResponseDto);
		return num;
	}

//	https://api.themoviedb.org/3/movie/823464/credits?language=en-US&api_key="+ key
//	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko-KR&sort_by=popularity.des&page="+page;
	public List<CreditDTO> getCredits(String contId) {
		String apiUrl = "https://api.themoviedb.org/3/movie/"+contId+"/credits?language=en-US&api_key=" + key;
		CreditResponseDTO creditResponse = restTemplate.getForObject(apiUrl, CreditResponseDTO.class);
		return creditResponse.getCast();
	}

	
}
