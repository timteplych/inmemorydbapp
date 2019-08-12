package ru.ttv.inmemorydbapp.api.db;

import ru.ttv.inmemorydbapp.entity.Entity;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public interface ProcessingAPI {

    void init();

    Entity getItem(Entity entity);

    int addItem(Entity entity);

    int deleteItem(Entity entity);

    int updateItem(Entity entity);

}
