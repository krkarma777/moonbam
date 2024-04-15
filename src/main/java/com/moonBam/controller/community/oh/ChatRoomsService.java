package com.moonBam.controller.community.oh;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomsService {

    private final ChatRoomsDAO chatRoomsDAO;

    public int create(ChatRoomCreateRequestDTO requestDTO) {
        return 1;
    }

//    public int update(ChatRoomUpdateRequestDTO requestDTO) {
//        return chatRoomsDAO.update(requestDTO);
//    }
//
//    public int delete(int id) {
//        return chatRoomsDAO.delete(id);
//    }

//    public List<ChatRoomDTO> selectAll() {
//        return chatRoomsDAO.selectAll();
//    }

//    public List<ChatRoomDTO> selectByCategory(String category) {
//        return chatRoomsDAO.selectByCategory(category);
//    }
//
//    public ChatRoomDTO selectById(int id) {
//        return chatRoomsDAO.selectById(id);
//    }
//
//    public ChatRoomDTO selectByLocation(String location) {
//        return chatRoomsDAO.selectByLocation(location);
//    }
}
