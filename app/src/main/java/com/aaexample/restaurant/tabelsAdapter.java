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

public class tabelsAdapter extends ArrayAdapter<ListItem> {
    ArrayList<ListItem> lst = new ArrayList();

    public tabelsAdapter(@NonNull Context context, @NonNull ArrayList<ListItem> ListItems) {
        super(context, 0, ListItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.table, parent, false);
        }

        ListItem currentListItem = getItem(position);

        TextView tableN = listItemView.findViewById(R.id.tableNum);
        tableN.setText(currentListItem.getmTableNumber());
        ImageView tableImage = listItemView.findViewById(R.id.icon);

        tableImage.setImageResource(R.drawable.table1);

        /* if (currentTable.hasImage()) {
            tableImage.setImageResource(currentTable.getmImageResourceId());
            tableImage.setVisibility(View.VISIBLE);
        } else {
            tableImage.setVisibility(View.GONE);
        } */
        return listItemView;
    }
}
