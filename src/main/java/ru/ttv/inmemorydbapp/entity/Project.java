package ru.ttv.inmemorydbapp.entity;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class Project extends Entity {

    private String description;

    private String owner;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
