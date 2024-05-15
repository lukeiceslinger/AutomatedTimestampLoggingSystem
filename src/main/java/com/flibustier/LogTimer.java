package com.flibustier;

import com.flibustier.Service.TimestampService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LogTimer {
    private final TimestampService timestampService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private LocalDateTime lastLog = LocalDateTime.now();
    private Duration logInterval = Duration.ofSeconds(30);

    public LogTimer(TimestampService timestampService) {
        this.timestampService = timestampService;
    }

    @Scheduled(fixedRate = 1000)
    public void checkCondition() {
        LocalDateTime latestLog = LocalDateTime.now();

        if (isConditionMet() && isLogIntervalElapsed(latestLog)) {
            System.out.println(formatter.format(latestLog) + " Condition met.");
            System.out.println(formatter.format(latestLog) + " Last log = Latest Log");

            timestampService.saveTimestamp(latestLog);
            lastLog = latestLog;
        }
    }

    public boolean isConditionMet() {
        //Add your condition logic here
        return true;
    }

    private boolean isLogIntervalElapsed(LocalDateTime latestLog) {
        return Duration.between(lastLog, latestLog).compareTo(logInterval) >= 0;
    }

    public TimestampService getTimestampService() {
        return timestampService;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public LocalDateTime getLastLog() {
        return lastLog;
    }

    public void setLastLog(LocalDateTime lastLog) {
        this.lastLog = lastLog;
    }

    public Duration getLogInterval() {
        return logInterval;
    }

    public void setLogInterval(Duration logInterval) {
        this.logInterval = logInterval;
    }
}