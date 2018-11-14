package com.example.caesp.todolist;

public class Item {

    String name;
    String title;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Item(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String toString() {
        return name;
    }
}
