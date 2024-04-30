package com.moonBam.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {
	// chatContent를 파일에 저장하는 메서드
	public File saveChatContentToFile(String chatContent, String chatNum) {
		    String filePath = "src/main/resources/static/com/" + chatNum + ".txt";
		    File file = new File(filePath);
		    try {
		        if (!file.exists()) {
		            file.createNewFile(); // 파일이 존재하지 않으면 새로 생성
		            System.out.println("create file");
		        }
		        FileWriter writer = new FileWriter(file, true); // 두 번째 매개변수를 true로 하면 파일 끝에 추가한다.
		        writer.write(chatContent + "\n"); // 파일에 데이터 추가
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return file;
		}

		public String readChatContentFromFile(String filePath) {
	    StringBuilder stringBuilder = new StringBuilder();
	    try {
	        File file = new File(filePath);
	        if (!file.exists()) {
	            System.err.println("File not found: " + filePath);
	            return null;
	        }

	        BufferedReader reader = new BufferedReader(new FileReader(file));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            stringBuilder.append(line).append("\n");
	        }

	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return stringBuilder.toString();
	}
	
	public boolean deleteFile(String filePath) {
	    File file = new File(filePath);
	    if (file.exists()) {
	        return file.delete();
	    } else {
	        System.err.println("File not found: " + filePath);
	        return false;
	    }
	}
}
