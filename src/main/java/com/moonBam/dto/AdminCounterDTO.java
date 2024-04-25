package com.moonBam.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("AdminCounterDTO")
public class AdminCounterDTO {

	Date countDay;
	int counter;
	public Date getCountDay() {
		return countDay;
	}
	public void setCountDay(Date countDay) {
		this.countDay = countDay;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "AdminCounterDTO [countDay=" + countDay + ", counter=" + counter + "]";
	}
	public AdminCounterDTO(Date countDay, int counter) {
		super();
		this.countDay = countDay;
		this.counter = counter;
	}
	public AdminCounterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
