package dave;

import java.io.InputStream;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

// Read-only web API data source, expiration ignored
public class WebServiceStorage implements Storage<ExchangeRateList> {

    private final String apiUrl;

    private final ObjectMapper mapper = new ObjectMapper();

    public WebServiceStorage(String apiUrl) {
        this.apiUrl = apiUrl;
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

    public ExchangeRateList read() throws Exception {
        InputStream input = new URL(apiUrl).openStream();
        return mapper.readValue(input, ExchangeRateList.class);
    }

    public void write(ExchangeRateList value) {
    }
}

