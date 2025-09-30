package dave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ChainResourceTest {

    @Test
    public void testManualChain() throws Exception {

        // Create mock data
        String BASE = "USD";
        LocalDate DATE = LocalDate.parse("2025-08-25");
        Map RATES = Map.of("EUR", 0.92, "GBP", 0.78);

        // Set up a ManualStorage (representing API call)
        ManualStorage manual = new ManualStorage();

        // Empty, fresh MemoryStorage and FileStorage
        MemoryStorage<ExchangeRateList> mem = new MemoryStorage<>(3600);
        FileStorage<ExchangeRateList> file = new FileStorage<>("test_rates.json", 3600);

        // Build the storage chain
        List<Storage<ExchangeRateList>> chain = List.of(mem, file, manual);
        ChainResource<ExchangeRateList> resource = new ChainResource<>(chain);

        // Try reading (should ultimately get manualList and propagate to memory/file)
        ExchangeRateList result = resource.getValue();

        // Then:
        assertNotNull(result);
        assertEquals(BASE, result.base);
        assertEquals(RATES.get("EUR"), result.rates.get("EUR"));
        assertEquals(RATES.get("GBP"), result.rates.get("GBP"));
        System.out.println(mem.read().toString());
        System.out.println(file.read().toString());

    }

}
