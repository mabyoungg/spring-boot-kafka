package com.example.springbootkafka.domain.member.member.service;

import com.example.springbootkafka.domain.member.member.entity.Member;
import com.example.springbootkafka.domain.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Member join(String username, String password, String nickname) {
        return memberRepository.save(
                Member.builder()
                        .username(username)
                        .password(password)
                        .nickname(nickname)
                        .build()
        );
    }

    public long count() {
        return memberRepository.count();
    }
}
