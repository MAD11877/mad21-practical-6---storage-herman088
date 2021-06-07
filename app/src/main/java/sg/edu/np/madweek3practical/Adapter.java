package sg.edu.np.madweek3practical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/*
public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<User> data ;


    public Adapter(ArrayList<User> input){

        data = input;

    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        User listObjects = data.get(viewType);
        char lastC = listObjects.name.charAt(listObjects.name.length() - 1);


        if (Character.getNumericValue(lastC)==7){
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.xtrarecyclerview,parent,false);
            return new ViewHolder(item);


        }
        else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
            return new ViewHolder(item);
        }

    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User listObjects = data.get(position);

        holder.name.setText(listObjects.name);
        holder.description.setText(listObjects.description);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context =  v.getContext();
               AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(listObjects.name);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.putExtra("Name", listObjects.name);
                        intent.putExtra("Description", listObjects.description);
                        intent.putExtra("followed", listObjects.followed);


                        context.startActivity(intent);







                    }
                });
                builder.create();
                builder.show();

            }
        });

    }


    public int getItemCount(){
        return data.size();


    }*/
public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<User> data;

    public Adapter(ArrayList<User> input){
        data = input;
    }

    @NonNull
    @Override
    //insert layout res file to viewH
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;

        if(viewType == 7) {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.xtrarecyclerview,
                    parent,
                    false
            );
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.recyclerview,
                    parent,
                    false
            );
        }
        return new ViewHolder(item);
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(data.get(position).name.substring(data.get(position).name.length()-1));
    }

    @Override
    //each spef viewholder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User u = data.get(position);
        holder.name.setText(u.name);
        holder.description.setText(u.description);


        holder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Debug", "Image clicked");

                new AlertDialog.Builder(holder.image.getContext())
                        .setTitle("Profile")
                        .setMessage(u.name)
                        .setPositiveButton("View", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent viewProfile = new Intent(holder.image.getContext(), MainActivity.class);
                                viewProfile.putExtra("id", position);
                                holder.image.getContext().startActivity(viewProfile);
                            }
                        })
                        .setNegativeButton("Close", null)
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
