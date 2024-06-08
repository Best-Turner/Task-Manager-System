package org.example.model;

import java.time.LocalDateTime;

public class Task {
    private final int id;
    private final String name;
    private final String description;
    private LocalDateTime dateTimeCreation;


    public Task(int id, String name, String description, LocalDateTime dateTimeCreation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTimeCreation = dateTimeCreation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTimeCreation() {
        return dateTimeCreation;
    }

    public void setDateTimeCreation(LocalDateTime dateTimeCreation) {
        this.dateTimeCreation = dateTimeCreation;
    }
}
