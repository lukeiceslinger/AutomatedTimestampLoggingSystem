package com.flibustier.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class TimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private LocalDateTime latestLog;
    private LocalDateTime lastLog;
    private boolean printSuccessful;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLatestLog() {
        return latestLog;
    }

    public void setLatestLog(LocalDateTime latestLog) {
        this.latestLog = latestLog;
    }

    public LocalDateTime getLastLog() {
        return lastLog;
    }

    public void setLastLog(LocalDateTime lastLog) {
        this.lastLog = lastLog;
    }

    public boolean isPrintSuccessful() {
        return printSuccessful;
    }

    public void setPrintSuccessful(boolean printSuccessful) {
        this.printSuccessful = printSuccessful;
    }
}
