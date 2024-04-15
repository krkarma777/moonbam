//package com.moonBam.controller.community.oh;
//
//import com.chat.dto.chatRoom.ChatRoomCreateRequestDTO;
//import com.chat.dto.chatRoom.ChatRoomDTO;
//import com.chat.dto.chatRoom.ChatRoomUpdateRequestDTO;
//import lombok.RequiredArgsConstructor;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class ChatRoomsDAO {
//
//    private final SqlSessionTemplate session;
//
//    public int create(ChatRoomCreateRequestDTO requestDTO) {
//        return 1;
//    }
//
////    public int update(ChatRoomUpdateRequestDTO requestDTO) {
////        return session.update("ChatRoomMapper.update", requestDTO);
////    }
////
////    public int delete(int id) {
////        return session.delete("ChatRoomMapper.delete", id);
////    }
////
////    public List<ChatRoomDTO> selectAll() {
////        return session.selectList("ChatRoomMapper.selectAll");
////    }
////
////    public List<ChatRoomDTO> selectByCategory(String category) {
////        return session.selectList("ChatRoomMapper.selectByCategory", category);
////    }
////
////    public ChatRoomDTO selectById(int id) {
////        return session.selectOne("ChatRoomMapper.selectById", id);
////    }
////
////    public ChatRoomDTO selectByLocation(String location) {
////        return session.selectOne("ChatRoomMapper.selectByLocation", location);
////    }
//}
