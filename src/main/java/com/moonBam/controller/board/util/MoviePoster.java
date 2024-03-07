package com.moonBam.controller.board.util;

import java.util.ArrayList;
import java.util.List;

public class MoviePoster {
	
	List<String> hotList = new ArrayList<String>();
	List<String> newList = new ArrayList<String>();

	String board;
	
	public MoviePoster(String board) {
		super();
		this.board = board;
	}

	public List<String> getHotList() {
		if(board.contains("movie")) {
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20236180&source=https://www.moviechart.co.kr/assets/upload/movie/240119104842_5826.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20206946&source=https://www.moviechart.co.kr/assets/upload/movie/240109111502_5166.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20211477&source=https://www.moviechart.co.kr/assets/upload/movie/240123135102_3965.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20212668&source=https://www.moviechart.co.kr/assets/upload/movie/240123133115_8516.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20231415&source=https://www.moviechart.co.kr/assets/upload/movie/240123133959_7458.jpg");
			//5
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20247076&source=https://www.moviechart.co.kr/assets/upload/movie/240129173316_6727.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20234730&source=https://www.moviechart.co.kr/assets/upload/movie/240109113503_8513.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20235253&source=https://www.moviechart.co.kr/assets/upload/movie/231228091847_8085.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20234581&source=https://www.moviechart.co.kr/assets/upload/movie/240123132706_5788.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20246964&source=https://www.moviechart.co.kr/assets/upload/movie/240123132310_1328.jpg");
			//10
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20246966&source=https://www.moviechart.co.kr/assets/upload/movie/240123134758_4979.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20235834&source=https://www.moviechart.co.kr/assets/upload/movie/231221060205_7364.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20234114&source=https://www.moviechart.co.kr/assets/upload/movie/240102150246_5989.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20212866&source=https://www.moviechart.co.kr/assets/upload/movie/231103092101_5446.jpg");
			hotList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20229551&source=https://www.moviechart.co.kr/assets/upload/movie/240129174535_3147.jpg");
			//15
		}
		return hotList;
	}

	public void setHotList(List<String> hotList) {
		this.hotList = hotList;
	}

	public List<String> getNewList() {
		if(board.contains("movie")) {
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20211477&source=https://www.moviechart.co.kr/assets/upload/movie/240123135102_3965.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20212668&source=https://www.moviechart.co.kr/assets/upload/movie/240123133115_8516.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20226638&source=https://www.moviechart.co.kr/assets/upload/movie/240124132531_4236.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20231415&source=https://www.moviechart.co.kr/assets/upload/movie/240123133959_7458.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20234581&source=https://www.moviechart.co.kr/assets/upload/movie/240123132706_5788.jpg");
			//5
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20235904&source=https://www.moviechart.co.kr/assets/upload/movie/240123134358_2117.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20235910&source=https://www.moviechart.co.kr/assets/upload/movie/240130142857_2957.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20246953&source=https://www.moviechart.co.kr/assets/upload/movie/240130142541_6610.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20246966&source=https://www.moviechart.co.kr/assets/upload/movie/240123134758_4979.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20247038&source=https://www.moviechart.co.kr/assets/upload/movie/240119110848_5875.jpg");
			//10
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20247076&source=https://www.moviechart.co.kr/assets/upload/movie/240129173316_6727.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20234683&source=https://www.moviechart.co.kr/assets/upload/movie/240130143337_9275.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20236697&source=https://www.moviechart.co.kr/assets/upload/movie/240123135502_5374.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20247063&source=https://www.moviechart.co.kr/assets/upload/movie/240201135050_9448.jpg");
			newList.add("https://www.moviechart.co.kr/thumb?width=178&height=267&m_code=20247219&source=https://www.moviechart.co.kr/assets/upload/movie/240130143800_6331.jpg");
			//15
		}
		return newList;
	}

	public void setNewList(List<String> newList) {
		this.newList = newList;
	}
	
	
	
	
	

}
