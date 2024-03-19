package com.example.springbootkafka.domain.post.post.author.service;

import com.example.springbootkafka.domain.post.post.author.repository.AuthorRepository;
import com.example.springbootkafka.domain.post.post.author.entity.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }
}
