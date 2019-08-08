package ru.ttv.inmemorydbapp.command;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public class UpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        repository.updateItem(entity);
    }

}
