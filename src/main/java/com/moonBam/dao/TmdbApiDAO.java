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

//	이번 주에 독일 극장에서 상영되는 영화를 찾고 있다고 가정해 보겠습니다. 쉽습니다. 이제 다음 쿼리를 작성할 수 있습니다.with_release_typeregion
//	https://api.themoviedb.org/3/discover/movie?
//	language=de-DE   // 
//	&region=DE
//	&release_date.gte=2016-11-16
//	&release_date.lte=2016-12-02
//	&with_release_type=2|3

//	release_type
//	1	첫날
//	2	연극 (제한적)
//	3	연극
//	4	디지털
//	5	물리적
//	6	TV
	
	// 한국 : KR
	// https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=KR&page=1&region=KR&release_date.gte=2016-11-16&release_date.lte=2024-04-01&sort_by=popularity.desc&with_release_type=2|3
	// tmdb에 api 요청 20개의 영화 데이터가 1페이지 단위로 반환됨
	public MovieResponseDTO getMoviePage(int page) {
//    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko-KR&sort_by=popularity.des&page="+page;
//    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko&region=KR&release_date.gte=2016-11-16&release_date.lte=2024-04-01&sort_by=popularity.desc&with_release_type=2|3&page="+page;
//    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko&region=KR&sort_by=popularity.desc&page="+page;
    	String apiUrl = "https://api.themoviedb.org/3/discover/movie?api_key="+ key +"&include_adult=false&include_video=false&language=ko&region=KR&with_release_type=2|3&sort_by=popularity.desc&page="+page;
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
