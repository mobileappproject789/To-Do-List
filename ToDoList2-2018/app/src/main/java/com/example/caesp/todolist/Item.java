package com.example.caesp.todolist;

import java.util.ArrayList;

public class Item {

    String It;
    private String title;



    @Override
    public String toString() {
        return It;
    }

    public Item(String item, String title) {
        this.It = item;
        this.title = title;

    }


    public String getIt(){
        return It;
    }

    public String gettitle() {
        return title;
    }


    public static ArrayList<Item> getitem() {
        ArrayList<Item> item = new ArrayList<>();


        return item;
    }
}
