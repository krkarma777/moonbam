package com.moonBam.controller.member;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moonBam.config.KISA_SEED_CBC;

@RestController
public class SecurityController {
	
    private static final byte[] pbszUserKey = "jthe!minthe@kee#".getBytes();	//반드시 16바이트(메뉴얼 44pg)
    private static final byte[] pbszIV = "hetalpsuemekete!".getBytes();			//반드시 16바이트(메뉴얼 44pg)
 
//    SEED_CBC_Encrypt 메서드
//    - 사용자키(pbszUserKey), 초기화 벡터(pbszIV), 암호화할 메세지(message), 메세지 오프셋과 길이를 입력한다. 
//    - 메시지를 패딩하여 블록 크기의 배수로 만들고, SEED_CBC 초기화 및 처리를 통해 암호화한다.
//
//    SEED_CBC_Decrypt 메서드
//    - 사용자키(pbszUserKey), 초기화 벡터(pbszIV), 복화할 메세지(message), 메세지 오프셋과 길이를 입력한다. 
//    - 복호화를 수행하기 전에 패딩을 확인하고, 패딩이 올바르지 않으면 null을 반환한다.
//    - SEED_CBC 초기화 및 처리를 통해 복호화를 수행하고, 결과로 원문을 반환한다.

    
    //암호화 시작(String 타입으로 출력)
    @GetMapping("/encrypt")
    public String encrypt(String mesg) {
    	byte[] byteMesg = doEncrypt(mesg);
    	String resultString = Base64.getEncoder().encodeToString(byteMesg);
    	return resultString;
    }
    
    //암호화 시스템
    public static byte[] doEncrypt(String StringMessage) {
        byte[] message = StringMessage.getBytes(StandardCharsets.UTF_8);
        byte[] byteEncryptedMessage = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, message, 0, message.length);
        return byteEncryptedMessage;
    }
    
    //복호화 시작(String 타입으로 암호를 받음)
    @GetMapping("/decrypt")
    public static String decrypt(String mesg) {
    	byte[] resultByteArray = Base64.getDecoder().decode(mesg);
    	String decryptedMessage = doDecrypt(resultByteArray);
    	return decryptedMessage;
    }
 
    //복호화 시스템
    public static String doDecrypt(byte[] encryptedMessage) {
    	byte[] decryptedMessage = KISA_SEED_CBC.SEED_CBC_Decrypt(pbszUserKey, pbszIV, encryptedMessage, 0, encryptedMessage.length);
        return new String(decryptedMessage);
    }
}