package dave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileStorageTest {

    private static final String TEST_FILE = "test_rates.json";

    @BeforeEach
    public void cleanUp() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @AfterEach
    public void destroy() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    public void testWritReadAndExpiration() throws Exception {
        FileStorage<ExchangeRateList> file = new FileStorage<>(TEST_FILE, 2);

        ExchangeRateList list = new ExchangeRateList();
        list.base = "USD";
        list.date = "2025-08-25";
        list.rates = Map.of("EUR", 0.92);

        assertFalse(Files.exists(Paths.get(TEST_FILE)));
        assertTrue(file.isExpired());

        file.write(list);
        assertTrue(Files.exists(Paths.get(TEST_FILE)));
        assertFalse(file.isExpired());

        ExchangeRateList result = (ExchangeRateList) file.read();

        assertEquals("USD", result.base);
        assertEquals(0.92, result.rates.get("EUR"));

        Thread.sleep(3000);
        assertTrue(file.isExpired());
        System.out.println("Writing file to: " + Paths.get(TEST_FILE).toAbsolutePath());

    }
}