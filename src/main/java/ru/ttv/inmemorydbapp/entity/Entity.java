package ru.ttv.inmemorydbapp.entity;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class Entity {

    private long id;

    private String title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
