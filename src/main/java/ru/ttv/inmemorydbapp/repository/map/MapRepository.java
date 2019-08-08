package ru.ttv.inmemorydbapp.repository.map;

import ru.ttv.inmemorydbapp.api.db.ProcessingAPI;
import ru.ttv.inmemorydbapp.entity.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public class MapRepository implements ProcessingAPI {

    private Map<Integer,Entity> repository;

    public void init() {
        repository = new HashMap<>();
    }

    @Override
    public int updateItem(Entity entity) {
        repository.put(entity.hashCode(), entity);
        return 1;
    }

    @Override
    public Entity getItem(Entity entity) {
        for (final Map.Entry<Integer, Entity> entry : repository.entrySet()){
            Entity current = entry.getValue();
            if(entity.getTitle().equals(current.getTitle())){
                return current;
            }
        }
        return null;
    }

    @Override
    public int addItem(Entity entity) {
        repository.put(entity.hashCode(),entity);
        return 1;
    }

    @Override
    public int deleteItem(Entity entity) {
        repository.remove(entity.hashCode());
        return 1;
    }
}
