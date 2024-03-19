package com.example.springbootkafka.domain.post.post.entity;

import com.example.springbootkafka.domain.post.post.author.entity.Author;
import com.example.springbootkafka.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Post extends BaseTime {
    @ManyToOne
    private Author author;
    private String title;
}
