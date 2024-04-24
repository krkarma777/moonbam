package com.moonBam.controller.adminpage;

import org.springframework.stereotype.Component;

@Component
public class AdminCounter {
	
	private int num=0;
	
	public AdminCounter() {};
	
	public void init() {
		num += 1;
		System.out.println("오늘의 접속자: " + num + "명");
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return num;
	}
}
