package ru.ttv.inmemorydbapp;

import ru.ttv.inmemorydbapp.system.ApplicationService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationService applicationService = new ApplicationService();
        applicationService.start();
    }
}
