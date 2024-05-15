package com.flibustier.Service;

import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LogRetrieverService {

    private final TimestampRepository timestampRepository;

    @Autowired
    public LogRetrieverService(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    public boolean isLastPrintSuccessful() {
        Optional<TimestampEntity> timestampOptional = timestampRepository.findById(1L);
        return timestampOptional.map(TimestampEntity::isPrintSuccessful).orElse(false);
    }

    public long calculateElapsedTimeSinceLastLog() {
        Optional<TimestampEntity> timestampOptional = timestampRepository.findById(1L);
        if (timestampOptional.isPresent()) {
            LocalDateTime lastLogTimestamp = timestampOptional.get().getLatestLog();
            LocalDateTime currentTimestamp = LocalDateTime.now();
            Duration duration = Duration.between(lastLogTimestamp, currentTimestamp);
            return duration.getSeconds();
        } else {
            return -1; // Indicates that no timestamp was found in the database
        }
    }

    public boolean shouldAttemptToPrintLog(LocalDateTime currentTimestamp) {
        Optional<TimestampEntity> timestampOptional = timestampRepository.findById(1L);
        return timestampOptional.map(timestampEntity -> {
            LocalDateTime latestLogTimestamp = timestampEntity.getLatestLog();
            return latestLogTimestamp.plusMinutes(5).isBefore(currentTimestamp) && !timestampEntity.isPrintSuccessful();
        }).orElse(true);
    }

}
