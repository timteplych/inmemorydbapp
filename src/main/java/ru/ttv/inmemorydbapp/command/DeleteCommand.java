package ru.ttv.inmemorydbapp.command;

import ru.ttv.inmemorydbapp.entity.Entity;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public class DeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        Entity current = repository.getItem(entity);
        repository.deleteItem(current);
    }

}
