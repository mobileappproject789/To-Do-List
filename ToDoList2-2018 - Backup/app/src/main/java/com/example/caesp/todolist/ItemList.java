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


    private ArrayList<Item> getItems;
    ArrayAdapter<Item> adapter;
    ArrayAdapter<Item> adapter2;
    ListView lvItems;

    Button button;
    Button button2;

    ArrayAdapter<Item> trashAdapter;
    ArrayList<Item> trash;
    ListView itemtrash;
    static TextView Title;
    String Tit;

    ArrayList<Item> item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Title = findViewById(R.id.Title);
        //set up Item List
        getItems = new ArrayList<Item>();
        adapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, getItems);
        adapter2 = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, item);
        lvItems = (ListView)  findViewById(R.id.lvItems);


        //Set up trash List
        trash = new ArrayList<Item>();
        trashAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_list_item_1, trash);
        itemtrash = findViewById(R.id.itemtrash);

        //Initialize Buttons
        button = findViewById(R.id.AddItem);
        button2 = findViewById(R.id.ClearTrash);

        lvItems.setAdapter(adapter2);
        adapter2.clear();
        itemtrash.setAdapter(trashAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ((TextView)findViewById(R.id.Title)).setText(extras.getString("Title"));
            Tit = extras.getString("Title");
            int i = 0;
            int size = (extras.getInt("size", 0));
            while (i <= size) {
                String name = (extras.getString("name" + i));
                if (name == null) {
                    break;
                }
                String title = (extras.getString("title" + i));
                Item it = new Item(name, title);
                getItems.add(it);
                i++;
            }
        }

        if (Tit != null) {
            String tit = Tit;
            getSelectedTitleData(tit);
        }


        //getItems.add(new Item("egg", "Test"));
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
                Item s = adapter2.getItem(position);
                trashAdapter.add(s);
                adapter2.remove(s);
                assert s != null;
                adapter2.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });

        itemtrash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item s = trashAdapter.getItem(position);
                adapter2.add(s);
                trash.remove(s);
                adapter2.notifyDataSetChanged();
                trashAdapter.notifyDataSetChanged();
            }
        });

    }



    private void getSelectedTitleData(String title) {


        for (Item it : getItems) {
            if (it.getTitle().equals(title)) {
                item.add(it);
            }

        }
        adapter2.notifyDataSetChanged();
    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        int a = 0;
            for (Item s : getItems){
                String name = s.getName();
                i.putExtra("name" + a, name);
                String title = s.getTitle();
                i.putExtra("title" + a, title);
                a++;
            }
        int size = getItems.size();
        i.putExtra("ListSize", size);
        setResult(2, i);
        finish();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Bundle extras = getIntent().getExtras();

        if (resultCode == 3) {
            String ite = (data.getStringExtra("item"));
            String title = Tit;
            Item it = new Item(ite, title);
            getItems.add(it);
            item.add(it);
            adapter2.notifyDataSetChanged();

            Intent i = new Intent(getBaseContext(), CreateItem.class);
            String message = "Please enter new item below.";
            i.putExtra("mess", message);
            startActivityForResult(i, 1);
        }


        if (resultCode == 1) {
            String ite = (data.getStringExtra("item"));
            String title = Tit;
            Item it = new Item(ite, title);
            getItems.add(it);
            item.add(it);
            adapter2.notifyDataSetChanged();

        }


    }

}


