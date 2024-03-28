package com.moonBam.controller.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonBam.dto.MemberDTO;
import com.moonBam.dto.board.PostDTO;
import com.moonBam.dto.board.PostUpdateRequestDTO;
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

    private PostDTO post;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockSession = new MockHttpSession();

        post = new PostDTO(
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
    }

    @Test
    void createPostWhenNotLoggedIn() throws Exception {
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
    void updatePostSuccessfully() throws Exception {
        MemberDTO loginUser = new MemberDTO(); // 로그인된 사용자 설정
        loginUser.setUserId("UserId123"); // 게시물 작성자와 동일한 ID
        mockSession.setAttribute("loginUser", loginUser);

        PostUpdateRequestDTO updateRequestDTO = new PostUpdateRequestDTO(); // 업데이트 요청 DTO 생성 및 필요한 값 설정
        updateRequestDTO.setPostTitle("Updated Title");
        updateRequestDTO.setPostText("Updated content of the post.");
        updateRequestDTO.setCategoryId(1L);

        given(postService.findById(1L)).willReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .session(mockSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.postID").value(1L));
    }

    @Test
    void updatePostWithUnauthorizedUser() throws Exception {
        MemberDTO anotherUser = new MemberDTO(); // 다른 사용자 설정
        anotherUser.setUserId("AnotherUser");
        mockSession.setAttribute("loginUser", anotherUser);

        PostUpdateRequestDTO updateRequestDTO = new PostUpdateRequestDTO(); // 업데이트 요청 DTO 생성 및 필요한 값 설정
        updateRequestDTO.setPostTitle("Attempted Update Title");
        updateRequestDTO.setPostText("Attempted update content of the post.");
        updateRequestDTO.setCategoryId(1L);

        given(postService.findById(1L)).willReturn(post); // postService의 findById 메소드가 호출되면 사전에 설정한 post 반환

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/post/1")
                        .session(mockSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequestDTO)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("글을 수정할 권한이 없습니다."));
    }
}
