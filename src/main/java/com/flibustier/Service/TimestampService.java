package com.flibustier.Service;

import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TimestampService {

    private final TimestampRepository timestampRepository;

    @Autowired
    public TimestampService(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    @Transactional
    public void saveTimestamp(LocalDateTime latestLog) {
        List<TimestampEntity> existingTimestamps = timestampRepository.findAll();

        if (!existingTimestamps.isEmpty()) {
            TimestampEntity existingTimestamp = existingTimestamps.get(0);
            existingTimestamp.setLastLog(existingTimestamp.getLatestLog());
            existingTimestamp.setLatestLog(latestLog);
            existingTimestamp.setPrintSuccessful(true);//                                                          printSuccessful is true at second entry
            timestampRepository.save(existingTimestamp);
        } else {

            TimestampEntity newTimestamp = new TimestampEntity();
            newTimestamp.setId(1L); // Set the ID to 1
            newTimestamp.setLatestLog(latestLog);
            newTimestamp.setLastLog(latestLog); //                                                         Set lastLog to latestLog since it's a new entry
            newTimestamp.setPrintSuccessful(false); //                                                  printSuccessful is initially false for new entries
            timestampRepository.save(newTimestamp);
        }
    }
}
