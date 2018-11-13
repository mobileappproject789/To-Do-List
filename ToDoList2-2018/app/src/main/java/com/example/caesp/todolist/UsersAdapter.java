package com.example.caesp.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<Item> {

    public UsersAdapter(ItemList context, ArrayList<Item> customArray) {
        super(context, 0, customArray);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
        }

        // Get the data item for this position
        Item user = (Item) getItem(position);

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tv1);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tv2);
        // Populate the data into the template view using the data object
        tvName.setText(user.getIt());
        String s = ItemList.Title.getText().toString();
        tvHome.setText(ItemList.Title.getText());
        // Return the completed view to render on screen
        return convertView;
    }
}

