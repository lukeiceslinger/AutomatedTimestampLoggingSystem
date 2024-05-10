package com.flibustier.Service;

import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
