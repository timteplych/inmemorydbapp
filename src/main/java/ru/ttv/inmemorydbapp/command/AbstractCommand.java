package ru.ttv.inmemorydbapp.command;

import ru.ttv.inmemorydbapp.api.db.ProcessingAPI;
import ru.ttv.inmemorydbapp.entity.Entity;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public abstract class AbstractCommand {

    protected ProcessingAPI repository;

    protected Entity entity;

    public abstract void execute();

    public void setRepository(ProcessingAPI repository) {
        this.repository = repository;
    }

    public ProcessingAPI getRepository() {
        return repository;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
