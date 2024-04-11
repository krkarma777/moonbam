package com.moonBam.util;

public class TimeParsing {
	/*
	 * delete
	 * 
	 * // 한줄에 public void CharReplace() { String originalString = "Hello, world!";
	 * 
	 * // 문자열에서 'H', 'l', 'o' 문자를 모두 '*'로 대체 String replacedString =
	 * originalString.replace("H", "*").replace("l", "*").replace("o", "*");
	 * System.out.println("Original string: " + originalString);
	 * System.out.println("Replaced string: " + replacedString); }
	 */
	
	
	// work good
// String timeFromTPP = "2024/03/14 18:00:00 - 2024/03/31 18:00:00";
	// ouput : String[] : "2024/03/14 18:00:00", "2024/03/31 18:00:00"
	public String[] tp2Arr(String timeFromTPP) {
		String timeArray[] = timeFromTPP.split(" - ");
		return timeArray;
	}

	// from db "2024-03-14 18:00:00"
	// writeDate, updateDate : array
	// startDate, endDate : array, tp
	public String[] db2Arr(String timeFromDB) {
		System.out.println("timeFromDB");
		String temp = timeFromDB.replace("-", "/").replace(" ", "/").replace(":", "/");
		String strArr[] = temp.split("/");
		System.out.print("\t");
		for(int i=0; i<strArr.length;i++) {
			System.out.println(strArr[i]);
		}
		return strArr;
	}
	
	// String timeFromTPP = "2024/03/14 18:00:00 - 2024/03/31 18:00:00";
	// to "2024/03/14 18:00:00 2024/03/31 18:00:00"
	// to startDate = "2024/03/14 18:00:00"
	// to endDate = "2024/03/31 18:00:00"
	public String tpParsing(String timeFromTPP) {
		String DBTime = timeFromTPP.replace(" - ", " ");
		return DBTime;
	}
	
	/*
	 * public void setDaet2Arr(AnnouncementDTO dto ) {
	 * dto.setWriteDateArr(dB2Arr(dto.getAnnoWriter())); dto.updateDateArr(
	 * dB2Arr(dto.getAnnoWriter())); dto.startDateArr( dB2Arr(dto.getAnnoWriter()));
	 * dto.endDateArr ( dB2Arr(dto.getAnnoWriter())); }
	 */
}
