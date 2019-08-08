package ru.ttv.inmemorydbapp.repository.db;

import ru.ttv.inmemorydbapp.api.db.DBConnectionAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class DBConnection implements DBConnectionAPI {

    private Connection conn;

    @Override
    public void init() {
        String url = "jdbc:h2:~/test";
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(url,"sa","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConn() {
        return conn;
    }

    @Override
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
