package Fav_I.IdolBom.Websocket;

import Fav_I.IdolBom.DTO.ChatMessageDTO;
import Fav_I.IdolBom.DTO.ChatRoomListGetResponse;
import Fav_I.IdolBom.DTO.MessageSubDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisSubscriber implements MessageListener {

    private final ObjectMapper objectMapper;
    private final SimpMessageSendingOperations messagingTemplate;

    @Override
    // redis에서 메시지 받았을 때 호출되는 메서드
    public void onMessage(Message message, byte[] pattern) {
        try {
            String publishMessage = new String(message.getBody());
        sendMessage(publishMessage);
        sendRoomList(publishMessage);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

    // redis에서 받은 메시지를 특정 채팅방에 발송
    public void sendMessage(String publishMessage) {
        try {
            // MessageSubDto에서 ChatMessageDTO를 추출하여 채팅방 구독자에게 발송
            ChatMessageDTO chatMessage =
                    objectMapper.readValue(publishMessage, MessageSubDTO.class).getChatMessageDTO();

            // 채팅방을 구독한 클라이언트에게 메시지 발송
            messagingTemplate.convertAndSend(
                    "/sub/chat/room/" + chatMessage.getChatRoomID(), chatMessage
            );

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }

    // 채팅방 리스트를 최신화하여 발송
    public void sendRoomList(String publishMessage) {
        try {
            MessageSubDTO dto = objectMapper.readValue(publishMessage, MessageSubDTO.class);

            List<ChatRoomListGetResponse> chatRoomListGetResponseList = dto.getApplicantList();
            List<ChatRoomListGetResponse> chatRoomListGetResponseListPartner = dto.getAgentList();

            Long userId = dto.getApplicantID();
            Long partnerId = dto.getAgentID();

            // 신청인 유저 채팅방 리스트 최신화 -> 내 계정에 보냄
            messagingTemplate.convertAndSend(
                    "/sub/chat/roomlist/" + userId, chatRoomListGetResponseList
            );

            // 대리인 계정에도 리스트 최신화 보냄.
            messagingTemplate.convertAndSend(
                    "/sub/chat/roomlist/" + partnerId, chatRoomListGetResponseListPartner
            );

        } catch (Exception e) {
            log.error("Exception {}", e);
        }
    }
}
