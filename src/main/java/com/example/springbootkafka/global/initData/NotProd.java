package com.example.springbootkafka.global.initData;

import com.example.springbootkafka.domain.member.member.service.MemberService;
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

    @Bean
    @Order(3)
    public ApplicationRunner initNotProd() {
        return new ApplicationRunner() {
            @Transactional
            @Override
            public void run(ApplicationArguments args) {
                if (memberService.count() > 0) return;

                memberService.join("user1", "1234", "유저1");
                memberService.join("user2", "1234", "유저2");
                memberService.join("user3", "1234", "유저3");
            }
        };
    }
}
