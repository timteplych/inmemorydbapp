package ru.ttv.inmemorydbapp.api.db;

import ru.ttv.inmemorydbapp.entity.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public interface DBProcessingAPI {

    void init();

    String getItem(String entityType, String param);

    int addItem(Entity entity);

    int deleteItem(String entityType, String param);

    void setConnection(Connection connection);

    void executeCommand(String command, String entityType, String dataString);
}
