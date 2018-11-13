package com.example.caesp.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity {

    static ArrayList<Item> customItems;
    ArrayAdapter<Item> adapter;
    ListView lvItems;

    Button button;
    Button button2;

    ArrayAdapter<String> trashAdapter;
    ArrayList<String> trash;
    ListView itemtrash;

    static TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Title = findViewById(R.id.Title);
        //set up Item List
        customItems = new ArrayList<Item>();
        adapter = new UsersAdapter(this, customItems);
        lvItems = (ListView)  findViewById(R.id.lvItems);
        lvItems.setAdapter(adapter);
        //Set up trash List
        trash = new ArrayList<String>();
        trashAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trash);
        itemtrash = findViewById(R.id.itemtrash);
        itemtrash.setAdapter(trashAdapter);
        //Initialize Buttons
        button = findViewById(R.id.AddItem);
        button2 = findViewById(R.id.ClearTrash);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ((TextView)findViewById(R.id.Title)).setText(extras.getString("Title"));
        }




        customItems.add(new Item("First Item", Title.getText().toString()));
        customItems.add(new Item("Second Item", Title.getText().toString()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CreateItem.class);
                String message = "Please enter new item below.";
                i.putExtra("mess", message);
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
                Item s = adapter.getItem(position);
                adapter.remove(s);
                String t = s.getIt();
                trashAdapter.add(t);
                adapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });

        itemtrash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = trashAdapter.getItem(position);
                customItems.add(new Item(s, ""));
                trash.remove(s);
                adapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });




    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        for (Item s : customItems){
            int b = 1;
            while (b < customItems.size()) {
                int size = customItems.size();
                String t = s.getIt();
                String u = s.gettitle();
                i.putExtra("ListSize", size);
                i.putExtra("item" + b, t);
                i.putExtra("title" + b, u);
                setResult(2, i);
                b = b + 1;
                finish();
            }
        }

    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        Bundle extras = getIntent().getExtras();

        if (extras == null) {

        }
        if (extras != null) {
            String item = (data.getStringExtra("item"));
            customItems.add(new Item(item, ""));
            adapter.notifyDataSetChanged();
        }

    }

}
