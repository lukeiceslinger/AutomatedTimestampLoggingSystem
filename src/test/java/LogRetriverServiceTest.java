
import com.flibustier.Entity.TimestampEntity;
import com.flibustier.Repository.TimestampRepository;
import com.flibustier.Service.LogRetrieverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
}
