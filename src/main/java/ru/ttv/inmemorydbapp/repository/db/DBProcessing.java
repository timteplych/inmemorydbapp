package ru.ttv.inmemorydbapp.repository.db;

import ru.ttv.inmemorydbapp.api.db.DBProcessingAPI;
import ru.ttv.inmemorydbapp.entity.Entity;
import ru.ttv.inmemorydbapp.entity.Project;
import ru.ttv.inmemorydbapp.entity.Task;
import ru.ttv.inmemorydbapp.system.ApplicationService;

import java.sql.*;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class DBProcessing implements DBProcessingAPI {

    private Connection connection;

    @Override
    public void init() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(getInitQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Entity getItem(Entity entity) {
        return null;
    }

    @Override
    public int deleteItem(Entity entity) {
        return 0;
    }

    @Override
    public int updateItem(Entity entity) {
        return 0;
    }

    @Override
    public int addItem(Entity entity) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(getInsertQuery(entity));
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    private String getDeleteQuery(String entityType, String param) {
        String sql = "";
        if(ApplicationService.PROJECT_ENTITY.equals(entityType)){
            sql = "DELETE FROM PROJECT WHERE title = '"+param+"'";
        }else{
            sql = "DELETE FROM TASK WHERE title = '"+param+"'";
        }
        return sql;
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private String getSelectQuery(String entityType, String param) {
        String sql = "";
        if(ApplicationService.PROJECT_ENTITY.equals(entityType)){
            sql = "SELECT id, title, description, owner FROM PROJECT WHERE title = '"+param+"'";
        }else{
            sql = "SELECT id, title FROM TASK WHERE title = '"+param+"'";
        }
        return sql;
    }

    private String getInsertQuery(Entity entity){
        String sql = "";
        if(entity instanceof Project){
            Project project = (Project) entity;
            sql = "INSERT INTO PROJECT(title, description, owner) " +
                    "VALUES ('"+project.getTitle()+"', '"+project.getDescription()+"', '"+project.getOwner()+"')";
        }

        return sql;
    }
    private String getInitQuery(){
        String sql =  "DROP TABLE PROJECT;" +
                "CREATE TABLE   PROJECT " +
                "(id INTEGER not NULL auto_increment, " +
                " title VARCHAR(255), " +
                " description VARCHAR(255), " +
                " owner VARCHAR(255), " +
                " PRIMARY KEY ( id ));"+
                "DROP TABLE TASK;" +
                "CREATE TABLE TASK "+
                "(id INTEGER not NULL, "+
                "title VARCHAR(255), "+
                "PRIMARY KEY ( id ))";
        return sql;
    }
}
