package com.example.caesp.todolist;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    Button button;
    Button button2;
    ArrayAdapter<String> trashAdapter;
    ListView itemtrash;
    ArrayList<String> trash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView)  findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        itemtrash = findViewById(R.id.itemtrash);
        trash = new ArrayList<String>();
        trashAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trash);
        itemtrash.setAdapter(trashAdapter);

        button = findViewById(R.id.AddItem);
        button2 = findViewById(R.id.ClearTrash);




        items.add("First Item");
        items.add("Second Item");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CreateItem.class);
                String message = "Enter your item below.";
                i.putExtra("messagefrommain", message);
                startActivityForResult(i, 1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trashAdapter.clear();
                trashAdapter.notifyDataSetChanged();
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = itemsAdapter.getItem(position);
                itemsAdapter.remove(s);
                trashAdapter.add(s);
                itemsAdapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });

        itemtrash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = trashAdapter.getItem(position);
                items.add(s);
                trash.remove(s);
                itemsAdapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });




    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        String item = (data.getStringExtra("item"));
        items.add(item);
        itemsAdapter.notifyDataSetChanged();
    }



}
