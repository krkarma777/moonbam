package com.moonBam.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

@Alias("MovieResponseDTO")
public class MovieResponseDTO {
        private int page;
        private int total_pages;
        private int total_results;
        private List<MovieResultResponseDTO> results;
        
        
		public int getPage() {
			return page;
		}

		public int getTotal_pages() {
			return total_pages;
		}

		public int getTotal_results() {
			return total_results;
		}

		public List<MovieResultResponseDTO> getResults() { 
			return results;
		}

		@Override
		public String toString() {
			return "MovieResponseDto [page=" + page + ", total_pages=" + total_pages + ", total_results="
					+ total_results + ", results=" + results + "]";
		}


}