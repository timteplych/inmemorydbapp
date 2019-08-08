package ru.ttv.inmemorydbapp.service;

import ru.ttv.inmemorydbapp.command.AbstractCommand;
import ru.ttv.inmemorydbapp.entity.Project;
import ru.ttv.inmemorydbapp.entity.Task;
import ru.ttv.inmemorydbapp.system.ApplicationService;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public class CommandService {

    private String[] params;

    private String entityType;

    public void execute(AbstractCommand command){
        if(ApplicationService.PROJECT_ENTITY.equals(entityType)){
            if(params.length >= 3){
                Project project = new Project();
                project.setTitle(params[0]);
                project.setDescription(params[1]);
                project.setOwner(params[2]);
                command.setEntity(project);
            }else if(params.length == 1){
                Project project = new Project();
                project.setTitle(params[0]);
                command.setEntity(project);
            }else{
                System.out.println("Incorrect params quantity");
                return;
            }
        }else if(ApplicationService.TASK_ENTITY.equals(entityType)){
            if(params.length >= 1){
                Task task = new Task();
                task.setTitle(params[0]);
                command.setEntity(task);
            }else{
                System.out.println("Incorrect params quantity");
                return;
            }
        }
        command.execute();
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
