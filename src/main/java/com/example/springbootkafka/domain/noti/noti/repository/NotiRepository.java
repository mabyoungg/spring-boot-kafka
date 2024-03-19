package com.example.springbootkafka.domain.noti.noti.repository;

import com.example.springbootkafka.domain.noti.noti.entity.Noti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiRepository extends JpaRepository<Noti, Long> {
}
