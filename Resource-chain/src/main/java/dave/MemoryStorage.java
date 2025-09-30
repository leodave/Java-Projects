package dave;

import java.time.LocalDateTime;

// In-memory cache with expiration
public class MemoryStorage<T> implements Storage<T> {

    private T value;

    private LocalDateTime expireAt;

    private final long expireTime;

    public MemoryStorage(long expireTime) {
        this.expireTime = expireTime;
        System.out.println("memory storage created!");
    }

    @Override
    public boolean isReadable() {
        System.out.println("memory storage is readable");
        return value != null && !isExpired();

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
    public T read() throws Exception {
        if (this.value == null) {
            System.out.println("memory storage is empty");
        } else {
            System.out.println("reading from memory " + value.toString());
        }

        return value;
    }

    @Override
    public void write(T value) throws Exception {
        this.value = value;
        this.expireAt = LocalDateTime.now().plusSeconds(expireTime);
        System.out.println("memory storage is written into");
    }
}
