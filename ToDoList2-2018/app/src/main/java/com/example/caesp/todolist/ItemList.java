package com.example.caesp.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class ItemList extends AppCompatActivity {

    static ArrayList<String> customItems;
    ArrayAdapter<String> adapter;
    ListView lvItems;

    Button button;
    Button button2;

    ArrayAdapter<String> trashAdapter;
    ArrayList<String> trash;
    ListView itemtrash;
    static TextView Title;
    String Tit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Title = findViewById(R.id.Title);
        //set up Item List
        customItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, customItems);
        lvItems = (ListView)  findViewById(R.id.lvItems);

        //Set up trash List
        trash = new ArrayList<String>();
        trashAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, trash);
        itemtrash = findViewById(R.id.itemtrash);

        //Initialize Buttons
        button = findViewById(R.id.AddItem);
        button2 = findViewById(R.id.ClearTrash);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ((TextView)findViewById(R.id.Title)).setText(extras.getString("Title"));
            Tit = extras.getString("Title");
            int i = 0;
            int size = (extras.getInt("size", 0));
            while (i <= size) {
                String item = (extras.getString("Item" + i));
                if (item == null) {
                    break;
                }
                customItems.add(item);
                i++;
            }
        }

        //CharSequence Titlefilter = Title.getText();
        //lvItems.setTextFilterEnabled(true);
        //ItemList.this.adapter.getFilter().filter(Titlefilter);
        //adapter.notifyDataSetChanged();


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
                String s = adapter.getItem(position);
                trashAdapter.add(s);
                adapter.remove(s);
                assert s != null;
                adapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });

        itemtrash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = trashAdapter.getItem(position);
                customItems.add(s);
                trash.remove(s);
                adapter.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });
        lvItems.setAdapter(adapter);
        itemtrash.setAdapter(trashAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        int a = 0;
            for (String s : customItems){
                String t = adapter.getItem(a);
                i.putExtra("item" + a, t);
                a++;
            }
        int size = customItems.size();
        i.putExtra("ListSize", size);
        setResult(2, i);
        finish();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        Bundle extras = getIntent().getExtras();
        if (data == null) {

        }
        if (data != null) {
            String item = (data.getStringExtra("item"));
            String title = Tit;
            customItems.add(title + "\n\n" + item);
            adapter.notifyDataSetChanged();

        }


    }

}
