package dave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MemoryStorageTest {

    @Test
    public void testWriteReadAndExpiration() throws Exception {
        MemoryStorage<String> mem = new MemoryStorage<>(2);

        assertNull(mem.read());
        assertTrue(mem.isExpired());

        mem.write("test-value");
        assertFalse(mem.isExpired());
        assertEquals("test-value", mem.read());

        Thread.sleep(3000);
        assertTrue(mem.isExpired());
    }
}