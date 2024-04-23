package com.moonBam.controller.content;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class KoficAPI {
	private String DailyBoxOfficeListURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
	@Value("${kofic.key}")
	private String key;
	private final SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");

	// Map -> QueryString
	public String makeQueryString(Map<String, String> paramMap) {
		final StringBuilder sb = new StringBuilder();

		paramMap.entrySet().forEach((entry) -> {
			if (sb.length() > 0) {
				sb.append('&');
			}
			sb.append(entry.getKey()).append('=').append(entry.getValue());
		});

		return sb.toString();
	}

	public List<JSONObject> dailyBoxOfficeList() {
		// 하루전 날짜 가져오기
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -1);

		Map<String, String> map = new HashMap<>();
		map.put("key", key);
		map.put("targetDt", date.format(cal.getTime()));
		map.put("itemPerPage", "10");
		
		List<JSONObject> dailyList = new ArrayList<>();
		
		try {
			URL requestURL = new URL(DailyBoxOfficeListURL + "?" + makeQueryString(map));
			HttpURLConnection con = (HttpURLConnection) requestURL.openConnection();

			// get으로 요청
			con.setRequestMethod("GET");
			con.setDoInput(true);

			// response. stream->jsonobject
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String readline = null;
			StringBuffer response = new StringBuffer();
			while ((readline = br.readLine()) != null) {
				response.append(readline);
			}

			// json 객체로 변환
			JSONObject responseBody = new JSONObject(response.toString());
			System.out.println("1" + responseBody);

			// 데이터 추출
			JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");

			String boxofficeType = boxOfficeResult.getString("boxofficeType");

			// 박스오피스 목록 출력
			JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
			Iterator<Object> iter = dailyBoxOfficeList.iterator();
			while (iter.hasNext()) {
				JSONObject boxOffice = (JSONObject) iter.next();
				System.out.printf("  %s - %s\n", boxOffice.get("rnum"), boxOffice.get("movieNm"), boxOffice.get("movieCd"));
				dailyList.add(boxOffice);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dailyList;
	}
}
