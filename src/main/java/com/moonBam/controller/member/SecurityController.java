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
 
    //암호화 시작(String 타입으로 출력)
    @GetMapping("/encrypt")
    public static String encrypt(String mesg) {
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