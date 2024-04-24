package com.moonBam.dto;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("MovieResultResponseDTO")
public class MovieResultResponseDTO {
//	컨텐츠 id : contId - id
//	컨텐츠 제목 : contTitle - title
//	감독id : producerId - 없음
//	설명 : description - overview
//	국가 : nation - original_language / production_countries (제작 국가)
//	출시일 : releaseDate - release_date
//	평균별점 : avgRate - vote_average
//	컨텐츠유형 : contType
//	컨텐츠이미지 : contImg - poster_path, backdrop_path

	
// append_to_response=credits 를 요청 쿼리에 추가하면 배우와 스태프 관련 정보들도 함께 받을 수 있다.
//	컨텐츠 이미지는 기본url + 사이즈 + 파일명 을 조합해서 구성해야함 
//	기본url과 사이즈는 https://developer.themoviedb.org/reference/configuration-detail 참조
	
// 첫 db 세팅 및 데이터 관리 로직에 대해 조언을 구하고 정립해야할듯함
	
//	인기순,트렌딩순, 최신순 등 유의미한 지표 순으로 db에 저장
//	

	
	private Long id;
    private String title;
    //private String producerID;
    private String overview;
//	private String original_language;
    //private String production_countries;
    private LocalDate release_date;
    
    private float vote_average;

    //private String popularity;
    private String poster_path;
    //private int vote_count;
    private double popularity;
    
    private List<Integer> genre_ids;
    private String genres;
	public MovieResultResponseDTO(Long id, String title, String overview, LocalDate release_date, float vote_average,
			String poster_path, double popularity, List<Integer> genre_ids, String genres) {
		super();
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.release_date = release_date;
		this.vote_average = vote_average;
		this.poster_path = poster_path;
		this.popularity = popularity;
		this.genre_ids = genre_ids;
		this.genres = genres;
	}
	public MovieResultResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MovieResultResponseDTO [id=" + id + ", title=" + title + ", overview=" + overview + ", release_date="
				+ release_date + ", vote_average=" + vote_average + ", poster_path=" + poster_path + ", popularity="
				+ popularity + ", genre_ids=" + genre_ids + ", genres=" + genres + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public LocalDate getRelease_date() {
		return release_date;
	}
	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}
	public float getVote_average() {
		return vote_average;
	}
	public void setVote_average(float vote_average) {
		this.vote_average = vote_average;
	}
	public String getPoster_path() {
		return poster_path;
	}
	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}
	public double getPopularity() {
		return popularity;
	}
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	public List<Integer> getGenre_ids() {
		return genre_ids;
	}
	public void setGenre_ids(List<Integer> genre_ids) {
		this.genre_ids = genre_ids;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
    
    
}

