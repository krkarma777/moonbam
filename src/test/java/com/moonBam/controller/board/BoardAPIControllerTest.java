package com.moonBam.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BoardAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private ObjectMapper objectMapper;
    private MockHttpSession mockSession;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockSession = new MockHttpSession();
    }

    @Test
    void createPostWhenNotLoggedIn() throws Exception {
        PostDTO post = getValidPostDTO();
        String content = objectMapper.writeValueAsString(post);

        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("로그인이 필요한 서비스입니다."));
    }

    @Test
    void createPostSuccessfully() throws Exception {
        MemberDTO loginUser = new MemberDTO(); // 적절한 MemberDTO 값 설정
        loginUser.setUserId("testUser");
        mockSession.setAttribute("loginUser", loginUser);

        PostDTO post = getValidPostDTO();

        String content = objectMapper.writeValueAsString(post);

        given(postService.save(any(PostDTO.class))).willReturn(1L); // postService의 save 메소드가 호출되면 1L을 반환하도록 설정

        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .session(mockSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postID").value(1L));
    }

    @Test
    void createPostWithValidationErrors() throws Exception {
        PostDTO postDTO = new PostDTO(); // 오류를 발생시킬 수 있는 값 설정
        String content = objectMapper.writeValueAsString(postDTO);

        mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("입력 값에 오류가 있습니다."));
    }

    private PostDTO getValidPostDTO() {
        // PostDTO 객체 생성
        PostDTO post = new PostDTO(
                1L, // postId
                "BoardName", // postBoard
                "UserId123", // userId
                1L, // contId
                "Post Title Example", // postTitle
                new Date(), // postDate
                null, // postEditDate
                "This is the content of the post.", // postText
                "Nickname", // nickname
                1L // categoryId
        );
        return post;
    }
}
