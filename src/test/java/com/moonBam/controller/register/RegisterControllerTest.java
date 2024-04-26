package com.moonBam.controller.register;

import com.moonBam.dto.MemberDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.moonBam.controller.member.MailController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest         //테스트를 하기 위한 annotation
@AutoConfigureMockMvc   //자동으로 MockMvc를 구성하여 테스트에서 사용
@Transactional          //각각의 테스트 메서드가 실행될 때 트랜잭션을 시작하고, 테스트가 끝나면 롤백
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;    //MockMvc는 Spring MVC테스트를 수행하는데 사용(컨트롤러 행동 시뮬레이션)

    @MockBean
    LoginService lServ;

    @MockBean
    RegisterService serv;

    @Autowired
    PasswordEncoder encoder;

    @MockBean
    MailController mc;

    @MockBean
    SecurityController sc;


    //각 테스트에서 사용되는 변수
    private MemberDTO mockDTO;
    private String test_userSignDate;

    @BeforeEach
    void setup() {
        //가입일 지정용 코드
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        test_userSignDate = dateFormat.format(currentDate);

        //dto에 test데이터 입력
        mockDTO = new MemberDTO();
            mockDTO.setUserId("test_userID");
            mockDTO.setUserPw("test_userPw");
            mockDTO.setNickname("test_nickname");
            mockDTO.setSecretCode("test_secretCode");
            mockDTO.setUserSignDate(test_userSignDate);

        //test용 암호화PW 설정
        String testUserPw = encoder.encode("test_userPw");
        System.out.println("testUserPw = " + testUserPw);
    }

    @Test
    void success_test() throws Exception {

        //아이디 중복 검사 시, 중복 아님
        when(serv.isUserIdDuplicate(mockDTO.getUserId())).thenReturn(false);

        //닉네임 중복 검사 시, 중복 아님
        when(serv.isUserNicknameDuplicate(mockDTO.getNickname())).thenReturn(false);

        //회원가입 진행 후 update 데이터 수 1출력
        when(serv.insertNewMember(any(MemberDTO.class))).thenReturn((1));

        //회원가입 과정 필요한 모든 데이터를 map 형태로 형성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("userId", "test_UserID");
            formData.add("userPw", "test_userPw");
            formData.add("userPwConfirm", "test_userPw");
            formData.add("nickname", "test_nickname");
            formData.add("secretCode", "test_secretCode");
            formData.add("userSignDate", test_userSignDate);

        // 실행
        mockMvc
                //RegisterData주소에 post로 전송
                .perform(post("/RegisterData")
                //사용될 데이터 지정
                .params(formData))
                //HTTP응답 상태 코드가 200인지 확인
                .andExpect(status().isOk())
                //응답 후의 이동 페이지가 어디로 가는지 지정
                .andExpect(view().name("member/Register/registerSuccess"))
                //모델에 mesg라는 속성이 없는지 확인(mesg가 없어야 성공적으로 가입)
                .andExpect(model().attributeDoesNotExist("mesg"));
    }


    @Test
    void fail_isUserIdDuplicate() throws Exception {

        //아이디 중복 검사 시, 아이디 중복
        //"existingUserId" / anyString() 기입
        when(serv.isUserIdDuplicate("existingUserID")).thenReturn(true);

        //회원가입 과정 필요한 모든 데이터를 map 형태로 형성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("userId", "existingUserID");
            formData.add("userPw", "test_userPw");
            formData.add("userPwConfirm", "test_userPw");
            formData.add("nickname", "test_nickname");
            formData.add("secretCode", "test_secretCode");
            formData.add("userSignDate", test_userSignDate);

        // 실행
        mockMvc
                //RegisterData주소에 post로 전송
                .perform(post("/RegisterData")
                //사용될 데이터 지정
                .params(formData))
                //HTTP응답 상태 코드가 200인지 확인
                .andExpect(status().isOk())
                //응답 후의 이동 페이지가 어디로 가는지 지정
                .andExpect(view().name("member/Register/registerFailure"))
                //모델에 mesg라는 속성이 있는지 확인(mesg가 있어야 실패)(model / request에 따라 다름)
                .andExpect(request().attribute("mesg", "이미 가입된 아이디입니다. 확인해주세요"));

    }

    @Test
    void fail_notEqualPassword() throws Exception {
        //아이디 중복 검사 시, 중복 아님
        when(serv.isUserIdDuplicate(anyString())).thenReturn(false);

        //회원가입 과정 필요한 모든 데이터를 map 형태로 형성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("userId", "test_UserID");
            formData.add("userPw", "test_userPw");
            formData.add("userPwConfirm", "notEqual_test_userPw");
            formData.add("nickname", "test_nickname");
            formData.add("secretCode", "test_secretCode");
            formData.add("userSignDate", test_userSignDate);

        // 실행
        mockMvc
                //RegisterData주소에 post로 전송
                .perform(post("/RegisterData")
                //사용될 데이터 지정
                .params(formData))
                //HTTP응답 상태 코드가 200인지 확인
                .andExpect(status().isOk())
                //응답 후의 이동 페이지가 어디로 가는지 지정
                .andExpect(view().name("member/Register/registerFailure"))
                //모델에 mesg라는 속성이 있는지 확인(mesg가 있어야 실패)
                .andExpect(request().attribute("mesg", "비밀번호가 일치하지 않습니다. 확인해주세요"));
    }

    @Test
    void fail_isDuplicateNickname() throws Exception {
        //아이디 중복 검사 시, 중복 아님
        when(serv.isUserIdDuplicate(anyString())).thenReturn(false);

        //닉네임 중복 검사 시, 중복 아님
        when(serv.isUserNicknameDuplicate(anyString())).thenReturn(true);

        //회원가입 과정 필요한 모든 데이터를 map 형태로 형성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("userId", "test_UserID");
            formData.add("userPw", "test_userPw");
            formData.add("userPwConfirm", "test_userPw");
            formData.add("nickname", "existing_nickname");
            formData.add("secretCode", "test_secretCode");
            formData.add("userSignDate", test_userSignDate);

        // 실행
        mockMvc
                //RegisterData주소에 post로 전송
                .perform(post("/RegisterData")
                //사용될 데이터 지정
                .params(formData))
                //HTTP응답 상태 코드가 200인지 확인
                .andExpect(status().isOk())
                //응답 후의 이동 페이지가 어디로 가는지 지정
                .andExpect(view().name("member/Register/registerFailure"))
                //모델에 mesg라는 속성이 있는지 확인(mesg가 있어야 실패)
                .andExpect(request().attribute("mesg", "이미 가입된 닉네임입니다. 확인해주세요"));

    }

    @Test
    void fail_noReason() throws Exception {
        //아이디 중복 검사 시, 중복 아님
        when(serv.isUserIdDuplicate(mockDTO.getUserId())).thenReturn(false);

        //닉네임 중복 검사 시, 중복 아님
        when(serv.isUserNicknameDuplicate(mockDTO.getNickname())).thenReturn(false);

        //회원가입 진행 후 update 데이터 수 1출력
        when(serv.insertNewMember(any(MemberDTO.class))).thenReturn((0));

        //회원가입 과정 필요한 모든 데이터를 map 형태로 형성
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("userId", "test_UserID");
        formData.add("userPw", "test_userPw");
        formData.add("userPwConfirm", "test_userPw");
        formData.add("nickname", "test_nickname");
        formData.add("secretCode", "test_secretCode");
        formData.add("userSignDate", test_userSignDate);

        // 실행
        mockMvc
                //RegisterData주소에 post로 전송
                .perform(post("/RegisterData")
                        //사용될 데이터 지정
                        .params(formData))
                //HTTP응답 상태 코드가 200인지 확인
                .andExpect(status().isOk())
                //응답 후의 이동 페이지가 어디로 가는지 지정
                .andExpect(view().name("member/Register/registerFailure"))
                //모델에 mesg라는 속성이 있는지 확인(mesg가 있어야 실패)
                .andExpect(request().attribute("mesg", "모종에 이유로 가입에 실패했습니다. 다시 한번 해주세요"));

    }

}