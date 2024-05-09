package com.flibustier.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "timestamp")
public class TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 1L;

    @Column(name = "lastLog")
    private LocalDateTime lastLog;

    @Column(name = "latestLog")
    private LocalDateTime latestLog;

    public LocalDateTime getLastLog() {
        return lastLog;
    }

    public void setLastLog(LocalDateTime lastLog) {
        this.lastLog = lastLog;
    }

    public LocalDateTime getLatestLog() {
        return this.latestLog;
    }

    public void setLatestLog(LocalDateTime latestLog) {
        this.latestLog = latestLog;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}