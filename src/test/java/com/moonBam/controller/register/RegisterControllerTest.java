package com.moonBam.controller.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.controller.member.MailController;
import com.moonBam.controller.member.SecurityController;
import com.moonBam.service.PostService;
import com.moonBam.service.member.LoginService;
import com.moonBam.service.member.RegisterService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RegisterControllerTest {
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LoginService lServ;
    @MockBean
    RegisterService serv;
	
    @MockBean
	PasswordEncoder encoder;
	
    @MockBean
	MailController mc;
	
    @MockBean
	SecurityController sc;
    
    
    
}
