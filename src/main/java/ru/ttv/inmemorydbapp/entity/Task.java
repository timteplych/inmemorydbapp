package ru.ttv.inmemorydbapp.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author Teplykh Timofey  31.07.2019
 */
public class Task extends Entity {

    private String title;

    private String description;

    private Date date;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date);
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
