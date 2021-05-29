package com.aaexample.restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.aaexample.restaurant.classes.Commande;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class CmdAdapter extends ArrayAdapter<Commande> {
    private Context context;
    private View CommandeView;

    public CmdAdapter(Context context, ArrayList<Commande> commandes) {
        super(context, 0, commandes);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommandeView = convertView;

        if(CommandeView == null) {
            CommandeView = LayoutInflater.from(getContext()).inflate(
                    R.layout.command_row, parent, false);
        }

        Commande currentCmd = getItem(position);
        TextView ordid = CommandeView.findViewById(R.id.order_id);
        ordid.setText(currentCmd.getId());

        TextView order_state = CommandeView.findViewById(R.id.order_state);
        order_state.setText("State : " + currentCmd.getEtat());

        TextView tableN = CommandeView.findViewById(R.id.tableNum);
        tableN.setText(currentCmd.getTable());
        
        TextView server = CommandeView.findViewById(R.id.server_name);
        server.setText(currentCmd.getUser());


        CommandeView.findViewById(R.id.readyBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference commandes = db.getReference("Commandes").child(currentCmd.getId());
                commandes.child("etat").setValue("Ready");
                Toast.makeText(context, "modification",Toast.LENGTH_SHORT).show();


              //  Toast.makeText(context, "ready", Toast.LENGTH_SHORT).show();
            }
        });

        CommandeView.findViewById(R.id.rowLine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, CommandDetails.class);
                i.putExtra("idcomd", currentCmd.getId());
                i.putExtra("table",currentCmd.getTable());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        return CommandeView;
    }
}
