package ru.ttv.inmemorydbapp.system;

import ru.ttv.inmemorydbapp.api.db.ProcessingAPI;
import ru.ttv.inmemorydbapp.command.*;
import ru.ttv.inmemorydbapp.repository.map.MapRepository;
import ru.ttv.inmemorydbapp.service.CommandService;

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
        CommandService commandService = new CommandService();
        ProcessingAPI repository = new MapRepository();
        repository.init();
        AbstractCommand command = null;
        showCommands();
        while (scanner.hasNext()){
            String userString = scanner.next();
            if (END_KEYWORD.equals(userString)) break;
            if(command == null){
                if(CREATE_COMMAND.equals(userString) || READ_COMMAND.equals(userString) || UPDATE_COMMAND.equals(userString) || DELETE_COMMAND.equals(userString)){
                    if(CREATE_COMMAND.equals(userString)){
                        command = new CreateCommand();
                    }else if (READ_COMMAND.equals(userString)){
                        command = new ReadCommand();
                    }else if(UPDATE_COMMAND.equals(userString)){
                        command = new UpdateCommand();
                    }else if (DELETE_COMMAND.equals(userString)){
                        command = new DeleteCommand();
                    }
                    System.out.println("Enter entity type - '"+PROJECT_ENTITY+"' or '"+TASK_ENTITY+"'");
                }else{
                    System.out.println("Incorrect command!");
                }
            }else if(command.getRepository() == null){
                if(PROJECT_ENTITY.equals(userString) || TASK_ENTITY.equals(userString)){
                    if(PROJECT_ENTITY.equals(userString)){
                        command.setRepository(repository);
                    }else if(TASK_ENTITY.equals(userString)){
                        command.setRepository(repository);
                    }
                    commandService.setEntityType(userString);
                    System.out.println("Enter parameter string with ; delimiter");
                }else{
                    System.out.println("Incorrect entity type!");
                }
            }else{
                commandService.setParams(userString.split(";"));
                commandService.execute(command);
                command = null;
                showCommands();
            }
        }
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
