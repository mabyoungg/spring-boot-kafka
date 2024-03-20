package com.example.springbootkafka.domain.noti.noti.eventListener;

import com.example.springbootkafka.domain.noti.noti.service.NotiService;
import com.example.springbootkafka.global.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class NotiEventListener {
    private final NotiService notiService;

    @EventListener
    @Async
    public void listen(PostCreatedEvent event) {
        notiService.postCreated(event.getPost());
    }

    @KafkaListener(topics = "chat-room-1", groupId = "1")
    public void consume(String message) {
        System.out.println("message: " + message);
    }
}
