package dave;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

// File cache storing JSON, with expiration
public class FileStorage<T> implements Storage {

    private final String filename;

    private final long expireSeconds;

    private final ObjectMapper mapper = new ObjectMapper();

    private LocalDateTime expireAt;

    private T cache;

    public FileStorage(String filename, long expireSeconds) {
        this.filename = filename;
        this.expireSeconds = expireSeconds;
        System.out.println("file storage created: ");
    }

    @Override
    public boolean isReadable() {

        return Files.exists(Paths.get(filename)) && cache != null && !isExpired();
    }

    @Override
    public boolean isWritable() {

        System.out.println("memory storage is writable");
        return true;
    }

    @Override
    public boolean isExpired() {
        return expireAt == null || LocalDateTime.now().isAfter(expireAt);
    }

    @Override
    public Object read() throws Exception {
        if (cache == null) {
            cache = (T) mapper.readValue(Files.readAllBytes(Paths.get(filename)), ExchangeRateList.class);
            expireAt = LocalDateTime.now().plusSeconds(expireSeconds);
        }
        return cache;
    }

    @Override
    public void write(Object value) throws Exception {
        Files.write(Paths.get(filename), mapper.writeValueAsBytes(value));
        expireAt = LocalDateTime.now().plusSeconds(expireSeconds);
        cache = (T) value;
        System.out.println("writing into file");
    }
}
