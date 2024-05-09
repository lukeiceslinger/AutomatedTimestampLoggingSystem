package com.flibustier.Service;

import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimestampService {

    private final TimestampRepository timestampRepository;

    @Autowired
    public TimestampService(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    public void saveTimestamp(LocalDateTime latestLog) {
        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setLatestLog(latestLog);
        timestampEntity.setLastLog(timestampEntity.getLatestLog()); // Update lastLog to latestLog
        timestampRepository.save(timestampEntity); // Save the timestamp to the database
    }
}
