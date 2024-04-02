package com.moonBam.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
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

    @Test
    void deletePostSuccessfully() throws Exception{

        PostDTO existingPost = new PostDTO();
        Long existingPostId = 1L;
        existingPost.setPostId(existingPostId);
        existingPost.setUserId("test-user");
        given(postService.findById(existingPostId)).willReturn(existingPost);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("test-user");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/" + existingPostId)
                        .sessionAttr("loginUser", memberDTO))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("삭제가 완료되었습니다."));
    }

    @Test
    void deletePostWhenNotLoggedIn() throws Exception{
        PostDTO existingPost = new PostDTO();
        Long existingPostId = 1L;
        existingPost.setPostId(existingPostId);
        given(postService.findById(existingPostId)).willReturn(existingPost);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId(("test-user"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/" + existingPostId))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("로그인이 필요한 서비스입니다."));
    }
    @Test
    void deletePostWhenNotFound() throws Exception{
        //글이 존재하지 않는 상황을 가정
        //given
        Long nonExistingPostId = 9999L;

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("test-user");

        //when then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/" + nonExistingPostId)
                        .sessionAttr("loginUser",memberDTO))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("글이 존재하지않습니다."));
    }

    @Test
    void deletePostWhenUserHasNoPermission() throws  Exception{
        //글은 존재하는데 아이디가 일치하지 않는 상황을 가정
        PostDTO existingPost = new PostDTO();
        Long existingPostId = 1L;
        existingPost.setPostId(existingPostId);
        existingPost.setUserId("test-user");

        given(postService.findById(existingPostId)).willReturn(existingPost);

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("another-user");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/post/" + existingPostId)
                        .sessionAttr("loginUser",memberDTO))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("글을 삭제할 권한이 없습니다."));
    }

}

