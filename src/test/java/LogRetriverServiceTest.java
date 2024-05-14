
import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import com.flibustier.Service.LogRetrieverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LogRetrieverServiceTest {

    @Mock
    private TimestampRepository timestampRepository;

    @InjectMocks
    private LogRetrieverService logRetrieverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testIsLastPrintSuccessful_PrintSuccessful_ReturnsTrue() {

        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setId(1L);
        timestampEntity.setPrintSuccessful(true);

        when(timestampRepository.findById(1L)).thenReturn(Optional.of(timestampEntity));
        boolean result = logRetrieverService.isLastPrintSuccessful();

        assertTrue(result);
    }

    @Test
    void testIsLastPrintSuccessful_PrintUnsuccessful_ReturnsFalse() {

        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setId(1L);
        timestampEntity.setPrintSuccessful(false);

        when(timestampRepository.findById(1L)).thenReturn(Optional.of(timestampEntity));

        boolean result = logRetrieverService.isLastPrintSuccessful();

        assertFalse(result);
    }

    @Test
    void testIsLastPrintSuccessful_NoRecordInDatabase_ReturnsFalse() {

        when(timestampRepository.findById(1L)).thenReturn(Optional.empty());
        boolean result = logRetrieverService.isLastPrintSuccessful();
        assertFalse(result);
    }

    @Test
    void testCalculateElapsedTimeSinceLastLog_WithTimestampInDatabase_ReturnsElapsedTimeInSeconds() {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime lastLogTimestamp = currentTimestamp.minusMinutes(2);
        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setId(1L);
        timestampEntity.setLatestLog(lastLogTimestamp);

        when(timestampRepository.findById(1L)).thenReturn(Optional.of(timestampEntity));

        long elapsedTime = logRetrieverService.calculateElapsedTimeSinceLastLog();

        assertEquals(120, elapsedTime); // 120 seconds elapsed between 14:30:00 and 14:32:00
    }

    @Test
    void testCalculateElapsedTimeSinceLastLog_WithNoTimestampInDatabase_ReturnsNegativeOne() {

        when(timestampRepository.findById(1L)).thenReturn(Optional.empty());

        long elapsedTime = logRetrieverService.calculateElapsedTimeSinceLastLog();

        assertEquals(-1, elapsedTime); // Indicates no timestamp found in the database
    }

    @Test
    void testShouldAttemptToPrintLog_LastPrintSuccessful_ReturnsTrue() {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime latestLogTimestamp = LocalDateTime.now().minusMinutes(5);
        TimestampEntity timestampEntity = new TimestampEntity();

        timestampEntity.setId(1L);
        timestampEntity.setLatestLog(latestLogTimestamp);
        timestampEntity.setPrintSuccessful(true);

        when(timestampRepository.findById(1L)).thenReturn(Optional.of(timestampEntity));

        boolean result = logRetrieverService.shouldAttemptToPrintLog(currentTimestamp);

        assertTrue(result);
    }

}
