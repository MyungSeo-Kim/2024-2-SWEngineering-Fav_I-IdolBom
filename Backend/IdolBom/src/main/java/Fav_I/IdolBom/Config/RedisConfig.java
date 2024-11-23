package Fav_I.IdolBom.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import RedisSubscriber;

@RequiredArgsConstructor
@Configuration
public class RedisConfig {
    @Bean
    // 채팅방을 위한 채널 설정
    public ChannelTopic channelTopic() {
        return new ChannelTopic("chatroom");
    }

    @Bean
    // 메시지 교환 방식을 지정하는 RedisTemplate 설정
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate;
    }

    @Bean
    // redis에 publish된 메시지를 처리하는 리스너 설정 - channel topic "chatroom"을 구독하는 컨테이너
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,
            MessageListenerAdapter listenerAdapterChatRoomList) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(listenerAdapterChatRoomList, new ChannelTopic("chatroom"));
        return container;
    }



    @Bean
    public MessageListenerAdapter listenerAdapterOnMessage(RedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "onMessage");
    }

    @Bean
    public MessageListenerAdapter listenerAdapterChatRoomList(RedisSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber, "sendRoomList");
    }
    public void addChannel(RedisMessageListenerContainer redisMessageListenerContainer, RedisSubscriber subscriber, String chatRoomId) {
        redisMessageListenerContainer.addMessageListener(listenerAdapterOnMessage(subscriber), new ChannelTopic(chatRoomId));
    }
}