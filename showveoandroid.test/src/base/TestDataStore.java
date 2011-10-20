package base;

import container.IDataStore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TestDataStore implements IDataStore {

    private Map<UUID, Object> _map;

    public TestDataStore() {
        _map = new HashMap<UUID, Object>();
    }

    public <T> UUID addData(T data) {
        UUID id = UUID.randomUUID();
        _map.put(id, data);
        return id;
    }

    public <T> T getData(UUID id, Class<T> type) {
        if (_map.containsKey(id))
            return (T) _map.get(id);
        return null;
    }
}
