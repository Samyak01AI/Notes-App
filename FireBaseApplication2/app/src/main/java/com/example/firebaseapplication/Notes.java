package com.example.firebaseapplication;

public class Notes {
    String title,description,id;

    public Notes(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Notes(String id) {
        this.id = id;
    }

    public Notes(String id, String description, String title) {
        this.id = id;
        this.description = description;
        this.title = title;
    }

    public Notes() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
