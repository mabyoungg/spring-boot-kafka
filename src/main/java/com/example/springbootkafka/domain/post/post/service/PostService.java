package com.example.springbootkafka.domain.post.post.service;

import com.example.springbootkafka.domain.member.member.entity.Member;
import com.example.springbootkafka.domain.member.member.service.MemberService;
import com.example.springbootkafka.domain.post.post.entity.Author;
import com.example.springbootkafka.domain.post.post.entity.Post;
import com.example.springbootkafka.domain.post.post.repository.PostRepository;
import com.example.springbootkafka.global.rsData.RsData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final MemberService memberService;

    @Transactional
    public RsData<Post> write(Author author, String title) {
        memberService.increasePostsCount(author.getId());

        return RsData.of(
                postRepository.save(
                        Post.builder()
                                .author(author)
                                .title(title)
                                .build()
                )
        );
    }

    public Author of(Member member) {
        return entityManager.getReference(Author.class, member.getId());
    }
}
