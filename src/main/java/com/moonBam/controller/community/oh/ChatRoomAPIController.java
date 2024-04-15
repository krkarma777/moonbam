//package com.moonBam.controller.community.oh;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import lombok.RequiredArgsConstructor;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/chatroom")
//public class ChatRoomAPIController {
//
//    private final ChatRoomsService chatRoomService;
//
//    // 채팅방 생성
//    @PostMapping
//    public ResponseEntity<?> createChatRoom(@RequestBody ChatRoomCreateRequestDTO requestDTO) {
//        if(chatRoomService.create(requestDTO) == 0) {
//            return ResponseEntity.badRequest().body("채팅방 생성에 실패하셨습니다.");
//        }
//        return ResponseEntity.ok().body("채팅방이 성공적으로 생성되었습니다.");
//    }
//
//	/*
//	 * // 채팅방 업데이트
//	 * 
//	 * @PutMapping("/{id}") public ResponseEntity<?> updateChatRoom(@PathVariable
//	 * int id, @RequestBody ChatRoomUpdateRequestDTO requestDTO) {
//	 * if(chatRoomService.update(requestDTO) == 0) { return
//	 * ResponseEntity.badRequest().body("채팅방 수정에 실패하셨습니다."); } return
//	 * ResponseEntity.ok().body("채팅방이 성공적으로 수정되었습니다."); }
//	 */
//
//    // 채팅방 삭제
//	/*
//	 * @DeleteMapping("/{id}") public ResponseEntity<?> deleteChatRoom(@PathVariable
//	 * int id) { if(chatRoomService.delete(id) == 0) { return
//	 * ResponseEntity.badRequest().body("채팅방 삭제에 실패하셨습니다."); } return
//	 * ResponseEntity.ok().body("채팅방이 성공적으로 삭제되었습니다."); }
//	 */
//
//	/*
//	 * // 모든 채팅방 조회
//	 * 
//	 * @GetMapping public ResponseEntity<List<ChatRoomDTO>> getAllChatRooms() {
//	 * List<ChatRoomDTO> chatRooms = chatRoomService.selectAll(); return
//	 * ResponseEntity.ok(chatRooms); }
//	 */
//
//	/*
//	 * // 카테고리별 채팅방 조회
//	 * 
//	 * @GetMapping("/category/{category}") public ResponseEntity<List<ChatRoomDTO>>
//	 * getChatRoomsByCategory(@PathVariable String category) { List<ChatRoomDTO>
//	 * chatRooms = chatRoomService.selectByCategory(category); return
//	 * ResponseEntity.ok(chatRooms); }
//	 */
//
//	/*
//	 * // 위치별 채팅방 조회
//	 * 
//	 * @GetMapping("/location/{location}") public ResponseEntity<ChatRoomDTO>
//	 * getChatRoomByLocation(@PathVariable String location) { ChatRoomDTO chatRoom =
//	 * chatRoomService.selectByLocation(location); if (chatRoom == null) { return
//	 * ResponseEntity.notFound().build(); } return ResponseEntity.ok(chatRoom); }
//	 */
//
//	/*
//	 * // ID로 채팅방 조회
//	 * 
//	 * @GetMapping("/{id}") public ResponseEntity<ChatRoomDTO>
//	 * getChatRoomById(@PathVariable int id) { ChatRoomDTO chatRoom =
//	 * chatRoomService.selectById(id); if (chatRoom == null) { return
//	 * ResponseEntity.notFound().build(); } return ResponseEntity.ok(chatRoom); }
//	 */
//}
