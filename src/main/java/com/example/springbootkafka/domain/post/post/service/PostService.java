package com.example.springbootkafka.domain.post.post.service;

import com.example.springbootkafka.domain.member.member.entity.Member;
import com.example.springbootkafka.domain.post.post.author.entity.Author;
import com.example.springbootkafka.domain.post.post.entity.Post;
import com.example.springbootkafka.domain.post.post.repository.PostRepository;
import com.example.springbootkafka.global.dto.chat.ChatMessageDto;
import com.example.springbootkafka.global.event.PostCreatedEvent;
import com.example.springbootkafka.global.rsData.RsData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationEventPublisher publisher;
    private final KafkaTemplate<Object, Object> template;

    @Transactional
    public RsData<Post> write(Author author, String title) {
        author.increasePostsCount();

        Post post = postRepository.save(
                Post.builder()
                        .author(author)
                        .title(title)
                        .build()
        );

        publisher.publishEvent(new PostCreatedEvent(this, post));
        template.send("chat-room-1", new ChatMessageDto(post.getId() + "번 글이 등록되었습니다."));

        return RsData.of(post);
    }

    public Author of(Member member) {
        return entityManager.getReference(Author.class, member.getId());
    }

    public Member of(Author author) {
        return entityManager.getReference(Member.class, author.getId());
    }
}
