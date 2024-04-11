package com.moonBam.util;

public class pageCalculation {
	private int[] pages = null;

	// currentPage= 현재페이지, lastPage= 마지막 페이지
	public pageCalculation() {
		super();
	}

	// currentPage= 현재페이지, lastPage= 마지막 페이지
	// 생성과 동시에 setPagesArray 호출
	public pageCalculation(int currentPage, int lastPage) {
		super();
		setPagesArray(currentPage, lastPage);
	}

	private void setPagesArray(int currentPage, int lastPage) {

		// 페이징 링크에 표시할 페이지 번호 개수
		int numberOfPagesToShow = 5;

		// 왼쪽에 표시될 페이지 번호의 시작값 계산
		int leftPageNumber = Math.max(currentPage - (numberOfPagesToShow / 2), 1);

		// 오른쪽에 표시될 페이지 번호의 시작값 계산
		int rightPageNumber = Math.min(leftPageNumber + numberOfPagesToShow - 1, lastPage);

		// 오른쪽에 표시될 페이지 번호 개수 보정
		if (rightPageNumber - leftPageNumber + 1 < numberOfPagesToShow) {
			leftPageNumber = Math.max(rightPageNumber - numberOfPagesToShow + 1, 1);
		}

		// 페이지 번호가 2 미만인 경우에는 항상 처음부터 5개의 페이지 번호 표시
		if (currentPage < 2) {
			rightPageNumber = Math.min(numberOfPagesToShow, lastPage);
		}

		// 화면 출력할 페이지를 array[] 저장
		this.pages = new int[rightPageNumber - leftPageNumber + 1];
		int temp = leftPageNumber;
		for (int i = 0, y = rightPageNumber - leftPageNumber + 1; i < y; i++) {
			this.pages[i] = temp++;
		}
	}

	public int[] getPages() {
		return pages;
	}
	
}
