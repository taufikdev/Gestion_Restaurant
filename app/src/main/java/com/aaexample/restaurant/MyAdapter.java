package com.aaexample.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class MyAdapter extends ArrayAdapter<ListItem> {


    public MyAdapter(@NonNull Context context, @NonNull ArrayList<ListItem> ListItems) {
        super(context, 0, ListItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.my_row, parent, false);
        }

        ListItem currentListItem = getItem(position);

        TextView tableN = listItemView.findViewById(R.id.plat_name);
        tableN.setText(currentListItem.getmTableNumber());

        TextView price = listItemView.findViewById(R.id.plat_price);
        price.setText(String.valueOf(currentListItem.getDetails()));

        ImageView tableImage = listItemView.findViewById(R.id.plat_image);

        //tableImage.setImageResource(R.drawable.table1);

        if (currentListItem.hasImage()) {
            tableImage.setImageResource(currentListItem.getmImageResourceId());
            tableImage.setVisibility(View.VISIBLE);
        } else {
            tableImage.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
