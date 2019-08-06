package ru.ttv.inmemorydbapp.system;

import ru.ttv.inmemorydbapp.api.db.DBConnectionAPI;
import ru.ttv.inmemorydbapp.api.db.DBProcessingAPI;
import ru.ttv.inmemorydbapp.service.db.DBConnection;
import ru.ttv.inmemorydbapp.service.db.DBProcessing;

import java.util.Scanner;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class ApplicationService {

    private static final String END_KEYWORD = "end";
    public static final String CREATE_COMMAND = "create";
    public static final String READ_COMMAND = "select";
    public static final String UPDATE_COMMAND = "update";
    public static final String DELETE_COMMAND = "delete";

    public static final String PROJECT_ENTITY = "project";
    public static final String TASK_ENTITY = "task";

    public void start(){
        Scanner scanner = new Scanner(System.in);
        DBConnectionAPI dbConnection = new DBConnection();
        dbConnection.init();
        DBProcessingAPI dbProcessing = new DBProcessing();
        dbProcessing.setConnection(dbConnection.getConn());
        dbProcessing.init();
        String currentCommand = "";
        String currentEntityType = "";
        String currentDataString = "";
        showCommands();
        while (scanner.hasNext()){
            String userString = scanner.next();
            if (END_KEYWORD.equals(userString)) break;
            if(currentCommand.isEmpty()){
                if(CREATE_COMMAND.equals(userString) || READ_COMMAND.equals(userString) || UPDATE_COMMAND.equals(userString) || DELETE_COMMAND.equals(userString)){
                    currentCommand = userString;
                    System.out.println("Enter entity type - '"+PROJECT_ENTITY+"' or '"+TASK_ENTITY+"'");
                }else{
                    System.out.println("Incorrect command!");
                }
            }else if(currentEntityType.isEmpty()){
                if(PROJECT_ENTITY.equals(userString) || TASK_ENTITY.equals(userString)){
                    currentEntityType = userString;
                    System.out.println("Enter parameter string with ; delimiter");
                }else{
                    System.out.println("Incorrect entity type!");
                }
            }else{
                currentDataString = userString;
                dbProcessing.executeCommand(currentCommand, currentEntityType, currentDataString);
                currentCommand = "";
                currentEntityType = "";
                currentDataString = "";
                showCommands();
            }
        }

        dbConnection.close();
        scanner.close();
    }

    private void showCommands(){
        System.out.println("--------------------------------------");
        System.out.println("Type "+ END_KEYWORD+" to exit program");
        System.out.println("To create entity - enter "+CREATE_COMMAND);
        System.out.println("To read entity - enter "+READ_COMMAND);
        System.out.println("To update entity - enter "+UPDATE_COMMAND);
        System.out.println("To delete entity - enter "+DELETE_COMMAND);
        System.out.println("--------------------------------------");
    }
}
