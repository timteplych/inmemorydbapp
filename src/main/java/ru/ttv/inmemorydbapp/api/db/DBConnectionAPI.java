package ru.ttv.inmemorydbapp.api.db;

import java.sql.Connection;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public interface DBConnectionAPI {
    void init();

    void close();

    Connection getConn();

    void setConn(Connection conn);
}
