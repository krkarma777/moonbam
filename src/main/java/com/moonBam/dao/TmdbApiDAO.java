package com.moonBam.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.moonBam.dto.MovieResponseDto;
import com.moonBam.dto.MovieResultResponseDto;


@Repository
public class TmdbApiDAO {
	
	@Value("${tmdb.key}")
	private String key;
	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	SqlSessionTemplate session;

	// tmdb에 api 요청 20개의 영화 데이터가 1페이지 단위로 반환됨
	public MovieResponseDto getMoviePage(int page) {
    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko-KR&sort_by=popularity.des&page="+page;
    	MovieResponseDto movieResponse = restTemplate.getForObject(apiUrl, MovieResponseDto.class);
    	//System.out.println(movieResponse);
    	//System.out.println("0번째값 테스트: "+ movieResponse.getResults().get(0));
        return movieResponse;
	}

	public int insertMovie(MovieResultResponseDto movieResultResponseDto) {
		int num = session.insert("insertMovie", movieResultResponseDto);
		return num;
	}

	
}
