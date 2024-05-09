import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import com.flibustier.Service.TimestampService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TimestampServiceTest {
    LocalDateTime currentTime = LocalDateTime.now();
    @Mock
    private TimestampRepository timestampRepository;

    @InjectMocks
    private TimestampService timestampService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveTimestamp() {

        timestampService.saveTimestamp(currentTime);
        ArgumentCaptor<TimestampEntity> argumentCaptor = ArgumentCaptor.forClass(TimestampEntity.class);
        verify(timestampRepository).save(argumentCaptor.capture());
        TimestampEntity capturedEntity = argumentCaptor.getValue();
        assertEquals(currentTime, capturedEntity.getLatestLog());
    }
}
