package com.flibustier.Repository;

import com.flibustier.Entity.TimestampEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TimestampRepository extends JpaRepository<TimestampEntity, Long> {
}
