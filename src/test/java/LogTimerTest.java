
import com.flibustier.LogTimer;
import com.flibustier.Service.TimestampService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.Duration;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;

class LogTimerTest {
    @Mock
    private TimestampService timestampService;
    private LogTimer logTimer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        logTimer = new LogTimer(timestampService);
    }

    @Test
    void testCheckCondition() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        doNothing().when(timestampService).saveTimestamp(any(LocalDateTime.class));
        logTimer.setLogInterval(Duration.ofSeconds(30));
        logTimer.setLastLog(now.minusSeconds(35));

        // Act
        logTimer.checkCondition();

        // Assert
        verify(timestampService, times(1)).saveTimestamp(any(LocalDateTime.class));
    }

    @Test
    void testCheckCondition_NoConditionMet() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        logTimer.setLogInterval(Duration.ofSeconds(30));
        logTimer.setLastLog(now);

        // Act
        logTimer.checkCondition();

        // Assert
        verify(timestampService, never()).saveTimestamp(any(LocalDateTime.class));
    }

    @Test
    void testCheckCondition_NoIntervalElapsed() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        logTimer.setLogInterval(Duration.ofSeconds(30));
        logTimer.setLastLog(now.minusSeconds(25));

        // Act
        logTimer.checkCondition();

        // Assert
        verify(timestampService, never()).saveTimestamp(any(LocalDateTime.class));
    }
}
