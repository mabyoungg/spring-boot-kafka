package com.example.springbootkafka.global.initData;

import com.example.springbootkafka.domain.member.member.entity.Member;
import com.example.springbootkafka.domain.member.member.service.MemberService;
import com.example.springbootkafka.domain.post.post.entity.Author;
import com.example.springbootkafka.domain.post.post.entity.Post;
import com.example.springbootkafka.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

@Profile("!prod")
@Configuration
@Slf4j
@RequiredArgsConstructor
public class NotProd {
    private final MemberService memberService;
    private final PostService postService;

    @Bean
    @Order(3)
    public ApplicationRunner initNotProd() {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) {
                if (memberService.count() > 0) return;

                Member memberUser1 = memberService.join("user1", "1234", "유저1").getData();
                Member memberUser2 = memberService.join("user2", "1234", "유저2").getData();
                Member memberUser3 = memberService.join("user3", "1234", "유저3").getData();

                Author author1 = postService.of(memberUser1);
                Author author2 = postService.of(memberUser2);
                Author author3 = postService.of(memberUser3);

                Post post1 = postService.write(author1, "제목1").getData();
                Post post2 = postService.write(author2, "제목1").getData();
                Post post3 = postService.write(author3, "제목1").getData();
            }
        };
    }
}
