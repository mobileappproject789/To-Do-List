package com.example.caesp.todolist;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateItem extends AppCompatActivity {

    Button addItem;
    Button addCreate;
    EditText Listitem;

    ConstraintLayout myLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        myLayout = (ConstraintLayout) findViewById(R.id.myLayout);

        animationDrawable = (AnimationDrawable) myLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            ((TextView)findViewById(R.id.textView)).setText(extras.getString("mess"));
        }

        Listitem = findViewById(R.id.editText);
        addItem = findViewById(R.id.Add);
        addCreate = findViewById(R.id.AddCreate);
        addCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent();
                String Item = "";
                if (Listitem.getText().toString() == null) {

                }else {
                    Item = Listitem.getText().toString();
                }
                i2.putExtra("item", Item);
                setResult(3, i2);
                finish();
            }
        });
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent();
                String Item = "";
                if (Listitem.getText().toString() == null) {

                }else {
                    Item = Listitem.getText().toString();
                }
                i2.putExtra("item", Item);
                setResult(1, i2);
                finish();
            }
        });


    }

    public void onBackPressed() {
        finish();
    }
}
