package Fav_I.IdolBom.Service;

import Fav_I.IdolBom.Config.RedisConfig;
import Fav_I.IdolBom.Config.RedisSubscriber;
import Fav_I.IdolBom.DTO.ChatRoomListGetResponse;
import Fav_I.IdolBom.DTO.MessageSubDTO;
import Fav_I.IdolBom.Entity.ChatRoom;
import Fav_I.IdolBom.Repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {
    private final RedisConfig redisConfig;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisSubscriber redisSubscriber;
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomService( 
            RedisConfig redisConfig,
            RedisMessageListenerContainer redisMessageListenerContainer,
            RedisSubscriber redisSubscriber,
            SimpMessageSendingOperations messagingTemplate, ChatRoomRepository chatRoomRepository) {
        this.redisConfig = redisConfig;
        this.redisMessageListenerContainer = redisMessageListenerContainer;
        this.redisSubscriber = redisSubscriber;
        this.messagingTemplate = messagingTemplate;
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     * 새로운 채팅방을 생성하고 Redis 구독 채널을 추가
     * @param chatRoomId 생성할 채팅방 ID
     */
    public ChatRoom createChatRoom(Integer chatRoomId) {
        // 동적으로 채팅방 생성 - addChannel 메서드를 통해 Redis 구독 채널 추가
        redisConfig.addChannel(redisMessageListenerContainer, redisSubscriber, String.valueOf(chatRoomId));
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setId(chatRoomId);

        return chatRoomRepository.save(chatRoom);
    }

    // 테스트용 임시 채팅방 생성
    public ChatRoom createTemporaryChatRoom(Long userId, Long agentId) {
        Integer tempChatRoomId = 123;

        ChatRoom chatRoom = createChatRoom(tempChatRoomId);

        MessageSubDTO messageSubDto = MessageSubDTO.builder()
                .applicantID(userId)
                .agentID(agentId)
                .build();
        return chatRoom;
    }
    /**
     * 신청인에게 채팅방 목록을 갱신합니다.
     * @param userId 신청인 ID
     * @param applicantList 신청인의 채팅방 목록
     */
    public void updateApplicantChatRoomList(Long userId, List<ChatRoomListGetResponse> applicantList) {

//        MessageSubDTO messageSubDto = new MessageSubDTO();
//        messageSubDto.setApplicantList(applicantList);
        messagingTemplate.convertAndSend("/sub/chat/roomlist/" + userId, applicantList);
    }

    /**
     * 대리인에게 채팅방 목록을 갱신합니다.
     * @param agentId 대리인 ID
     * @param agentList 대리인의 채팅방 목록
     */
    public void updateAgentChatRoomList(Long agentId, List<ChatRoomListGetResponse> agentList) {

//        MessageSubDTO messageSubDto = new MessageSubDTO();
//        messageSubDto.setAgentList(agentList);
        messagingTemplate.convertAndSend("/sub/chat/roomlist/" + agentId, agentList);
    }
}
