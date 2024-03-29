package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("MovieResponseDto")
public class MovieResponseDto {
        private int page;
        private int total_pages;
        private int total_results;
        private List<MovieResultResponseDto> results;
        
		public int getPage() {
			return page;
		}

		public int getTotal_pages() {
			return total_pages;
		}

		public int getTotal_results() {
			return total_results;
		}

		public List<MovieResultResponseDto> getResults() { 
			return results;
		}

		@Override
		public String toString() {
			return "MovieResponseDto [page=" + page + ", total_pages=" + total_pages + ", total_results="
					+ total_results + ", results=" + results + "]";
		}


}