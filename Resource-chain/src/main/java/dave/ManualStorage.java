package dave;

import java.util.HashMap;

//Used instead of WebServiceStorage using real Api call
public class ManualStorage implements Storage<ExchangeRateList> {

    private ExchangeRateList fakeList;

    public ManualStorage() {
        fakeList = new ExchangeRateList();
        fakeList.base = "USD";
        fakeList.date = "2025-08-25";
        fakeList.rates = new HashMap<>();
        fakeList.rates.put("EUR", 0.92);
        fakeList.rates.put("GBP", 0.78);
        // Add more as needed for your tests
        System.out.println();
    }

    public boolean isReadable() {
        return true;
    }

    public boolean isWritable() {
        return false;
    }

    public boolean isExpired() {
        return false;
    }

    public ExchangeRateList read() {
        return fakeList;
    }

    public void write(ExchangeRateList value) {
        // No operation; it's read-only
    }
}

