package dave;

import java.util.List;

// Main resource handler for getting/propagating a value of type T
public class ChainResource<T> {

    private final List<Storage<T>> storages;

    public ChainResource(List<Storage<T>> storages) {
        this.storages = storages;
        System.out.println("chain resource being created");
    }

    // Read value in chain, from memory -> file -> api call(manual-storage)
    public T getValue() throws Exception {
        T value = null;
        for (int i = 0; i < storages.size(); i++) {
            Storage<T> storage = storages.get(i);
            if (storage.isReadable() && !storage.isExpired()) {
                value = storage.read();
                System.out.println("storage is read from : " + storage.toString());
                break;
            }
        }

        // Propagate/ write data up the chain
        if (value != null) {
            for (Storage<T> storage : storages) {
                if (storage.isWritable()) {
                    System.out.println("storage is written to : " + storage.toString());
                    storage.write(value);
                }
            }
        }
        return value;
    }

}
