package com.example.springbootkafka.global.event;

import com.example.springbootkafka.domain.post.post.entity.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PostCreatedEvent extends ApplicationEvent {
    private final Post post;

    public PostCreatedEvent(Object source, Post post) {
        super(source);
        this.post = post;
    }
}
