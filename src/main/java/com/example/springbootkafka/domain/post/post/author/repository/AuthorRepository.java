package com.example.springbootkafka.domain.post.post.author.repository;

import com.example.springbootkafka.domain.post.post.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
