package com.moonBam.dto;

import org.apache.ibatis.type.Alias;

import com.moonBam.util.TimeParsing;

@Alias("AnnouncementDTO")
public class AnnouncementDTO {
	private int annoNum;
	public String annoTitle;
	private String annoText;
	private String annoWriter;
	private String writeDate;
	private String updateDate;
	private String startDate;
	private String endDate;
	private String popup;
	private String category;

	private String[] writeDateArr;
	private String[] updateDateArr;
	private String[] startDateArr;
	private String[] endDateArr;

	private String[] dateName = { "년", "월", "일", "시", "분", "초" };

	public String[] getDateName() {
		return dateName;
	}

	public void setDateName(String[] dateName) {
		this.dateName = dateName;
	}

	public AnnouncementDTO() {
		super();
		System.out.println("AnnouncementDTO.AnnouncementDTO()");
	}

	public AnnouncementDTO(int annoNum, String annoTitle, String annoText, String annoWriter, String writeDate,
			String updateDate, String startDate, String endDate, String popup, String category) {
		super();
		System.out.println("AnnouncementDTO.AnnouncementDTO()");
		this.annoNum = annoNum;
		this.annoTitle = annoTitle;
		this.annoText = annoText;
		this.annoWriter = annoWriter;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;

		TimeParsing tp = new TimeParsing();
		this.writeDateArr = tp.db2Arr(this.writeDate);
		this.updateDateArr = tp.db2Arr(this.updateDate);
		this.startDateArr = tp.db2Arr(this.startDate);
		this.endDateArr = tp.db2Arr(this.endDate);

		this.popup = ("on".equals(popup)) ? popup : "no";

	}

	// insert constructor
	public AnnouncementDTO(int annoNum, String annoTitle, String annoText, String annoWriter, String startDate,
			String endDate, String popup, String category) {
		super();
		this.annoNum = annoNum;
		this.annoTitle = annoTitle;
		this.annoText = annoText;
		this.annoWriter = annoWriter;
		this.startDate = startDate;
		this.endDate = endDate;
		this.category = category;

		this.popup = ("on".equals(popup)) ? popup : "no";
	}

	@Override
	public String toString() {
		return "AnnouncementDTO [annoNum=" + annoNum + ", annoTitle=" + annoTitle + ", annoText=" + annoText
				+ ", annoWriter=" + annoWriter + ", writeDate=" + writeDate + ", updateDate=" + updateDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", popup=" + popup + ",category=" + category + "]";
	}

	public int getAnnoNum() {
		return annoNum;
	}

	public void setAnnoNum(int annoNum) {
		this.annoNum = annoNum;
	}

	public String getAnnoTitle() {
		return annoTitle;
	}

	public void setAnnoTitle(String annoTitle) {
		this.annoTitle = annoTitle;
	}

	public String getAnnoText() {
		return annoText;
	}

	public void setAnnoText(String annoText) {
		this.annoText = annoText;
	}

	public String getAnnoWriter() {
		return annoWriter;
	}

	public void setAnnoWriter(String annoWriter) {
		this.annoWriter = annoWriter;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPopup() {
		return popup;
	}

	public void setPopup(String popup) {
		this.popup = popup;
	}
	// new add

	public String[] getWriteDateArr() {
		return writeDateArr;
	}

	public void setWriteDateArr(String[] writeDateArr) {
		this.writeDateArr = writeDateArr;
	}

	public String[] getUpdateDateArr() {
		return updateDateArr;
	}

	public void setUpdateDateArr(String[] updateDateArr) {
		this.updateDateArr = updateDateArr;
	}

	public String[] getStartDateArr() {
		return startDateArr;
	}

	public void setStartDateArr(String[] startDateArr) {
		this.startDateArr = startDateArr;
	}

	public String[] getEndDateArr() {
		return endDateArr;
	}

	public void setEndDateArr(String[] endDateArr) {
		this.endDateArr = endDateArr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTimes() {
		setTimes(this.writeDate, this.annoWriter, this.annoTitle, this.annoText);
	}

	public void setTimes(String writeDate, String updaString, String staString, String endString) {
		TimeParsing tp = new TimeParsing();
		this.writeDateArr = tp.db2Arr(writeDate);
		this.updateDateArr = tp.db2Arr(updateDate);
		this.startDateArr = tp.db2Arr(startDate);
		this.endDateArr = tp.db2Arr(endDate);
	}
}