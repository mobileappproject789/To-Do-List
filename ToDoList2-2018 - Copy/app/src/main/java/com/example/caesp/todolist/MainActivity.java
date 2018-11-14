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


    ListView ListofList;
    ArrayList<String> Lists;
    ArrayAdapter<String> ListAdapter;
    Button button;
    Button button2;
    ArrayList<Item> getItems = new ArrayList<>();
    int Size = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListofList = findViewById(R.id.LofL);
        Lists = new ArrayList<String>();
        ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Lists);
        ListofList.setAdapter(ListAdapter);

        
        button = findViewById(R.id.AddList);
        Lists.add("Test");

        ListofList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListofList.setSelection(position);
                return true;
            }
        });


        ListofList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), ItemList.class);
                String s = (String) ListofList.getItemAtPosition(position);
                i.putExtra("Title", s);
                i.putExtra("size", Size);
                int a = 0;
                for (Item it : getItems){
                    String name = it.getName();
                    i.putExtra("name" + a, name);
                    String title = it.getTitle();
                    i.putExtra("title" + a, title);
                    a++;
                }
                startActivityForResult(i, 2);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CreateItem.class);
                String message = "Please enter the name of the list below.";
                i.putExtra("mess", message);
                startActivityForResult(i, 1);
            }
        });



    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        Bundle extras = getIntent().getExtras();


            if (requestCode == 1) {
                String item = (data.getStringExtra("item"));
                Lists.add(item);
                ListAdapter.notifyDataSetChanged();
            }


        if (requestCode == 2) {
            ArrayList<Item> subject = new ArrayList<>();
            int i = 0;
            assert data != null;
            int size = (data.getIntExtra("ListSize", 0));
            Size = size;
            while (i <= (size - 1)) {
                String name = (data.getStringExtra("name" + i));
                String title = (data.getStringExtra("title" + i));
                Item it = new Item(name, title);
                subject.add(it);

                i++;
            }

            getItems = subject;
            //
        }
    }


}
