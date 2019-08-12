package ru.ttv.inmemorydbapp.command;

/**
 * @author Teplykh Timofey  06.08.2019
 */
public class ReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println(repository.getItem(entity));
    }

}
