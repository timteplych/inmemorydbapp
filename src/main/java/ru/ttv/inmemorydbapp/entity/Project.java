package ru.ttv.inmemorydbapp.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(description, project.description) &&
                Objects.equals(owner, project.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, owner);
    }

    @Override
    public String toString() {
        return "Project{" +
                "description='" + description + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
