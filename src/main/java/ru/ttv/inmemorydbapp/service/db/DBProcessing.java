package ru.ttv.inmemorydbapp.service.db;

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
    public void executeCommand(String command, String entityType, String dataString) {
        String[] params = dataString.split(";");
        if(ApplicationService.PROJECT_ENTITY.equals(entityType) && ApplicationService.CREATE_COMMAND.equals(command)){
            if(params.length >= 3){
                Project project = new Project();
                project.setTitle(params[0]);
                project.setDescription(params[1]);
                project.setOwner(params[2]);
                if(addItem(project) == 1){
                    System.out.println("added");
                };
            }else{
                System.out.println("Incorrect params quantity");
                return;
            }
        }else if(ApplicationService.TASK_ENTITY.equals(entityType)&& ApplicationService.CREATE_COMMAND.equals(command)){
            if(params.length >= 1){
                Task task = new Task();
                task.setTitle(params[0]);
                if(addItem(task) == 1){
                    System.out.println("added");
                };
            }
        }

        if(ApplicationService.READ_COMMAND.equals(command)){
            System.out.println(getItem(entityType, params[0]));
        }

        if(ApplicationService.DELETE_COMMAND.equals(command)){
            if(deleteItem(entityType, params[0]) == 1){
                System.out.println("deleted");
            };
        }
    }

    @Override
    public String getItem(String entityType, String param) {
        String resultString = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getSelectQuery(entityType, param));
            if (resultSet.next()){
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++ ) {
                    String colname = rsmd.getColumnName(i);
                     resultString += colname + ":"+resultSet.getString(colname)+" ";
                }
            }else{
                return "not found";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultString;
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


    @Override
    public int deleteItem(String entityType, String param) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(getDeleteQuery(entityType, param));
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
