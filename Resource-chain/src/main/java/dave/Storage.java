package dave;

// Interface for a generic data storage layer
public interface Storage<T> {

    boolean isReadable();

    boolean isWritable();

    boolean isExpired();

    T read() throws Exception;

    void write(T value) throws Exception;
    
}
