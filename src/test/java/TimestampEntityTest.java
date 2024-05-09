import com.flibustier.Entity.TimestampEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TimestampEntityTest {

    @Test
    public void testSetAndGetLastLog() {
        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setLastLog(null);                                                                                // Set last log to null
        Assertions.assertNull(timestampEntity.getLastLog());                                                             // Check if last log is null
    }

    @Test
    public void testSetAndGetLatestLog() {
        TimestampEntity timestampEntity = new TimestampEntity();
        timestampEntity.setLatestLog(null);                                                                              // Set latest log to null
        Assertions.assertNull(timestampEntity.getLatestLog());                                                           // Check if latest log is null
    }
}