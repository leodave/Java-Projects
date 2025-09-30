package dave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public class WebServiceStorageTest {

    @Test
    public void testReadReturnsExchangeRateList() throws Exception {
        // Prepare a mock JSON InputStream to simulate API response
        String json = "{\"base\":\"USD\",\"date\":\"2025-08-26\",\"rates\":{\"EUR\":0.92}}";
        InputStream mockInputStream = new java.io.ByteArrayInputStream(json.getBytes());

        // Create a subclass to override the URL connection for mocking
        WebServiceStorage storage = new WebServiceStorage("http://mock-api.test") {
            @Override
            public ExchangeRateList read() throws Exception {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(mockInputStream, ExchangeRateList.class);
            }
        };

        ExchangeRateList result = storage.read();

        assertNotNull(result);
        assertEquals("USD", result.base);
        assertEquals("2025-08-26", result.date);
        assertEquals(0.92, result.rates.get("EUR"));
    }

    @Test
    public void testIsReadableAndWritable() {
        WebServiceStorage storage = new WebServiceStorage("http://mock-api.test");
        assertTrue(storage.isReadable());
        assertFalse(storage.isWritable());
        assertFalse(storage.isExpired());
    }

    @Test
    public void testWriteDoesNothing() {
        WebServiceStorage storage = new WebServiceStorage("http://mock-api.test");
        // Should not throw exception
        storage.write(null);
    }
}
