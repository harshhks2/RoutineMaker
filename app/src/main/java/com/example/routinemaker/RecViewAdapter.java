package com.example.routinemaker;

import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder>{

    private ArrayList<Task> tasks = new ArrayList<>();
    Context context;

    public RecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_task,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
       holder.txtStartTime.setText(tasks.get(position).getStartTime());
       holder.txtEndTime.setText(tasks.get(position).getEndTime());
       holder.txtStartPeriod.setText(tasks.get(position).getStartPeriod());
       holder.txtEndPeriod.setText(tasks.get(position).getEndPeriod());
       holder.txtTaskAtHand.setText(tasks.get(position).getTaskAtHand());

       holder.txtStartTime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               MainActivity.setCvVisible(true);
               MainActivity.setPos(position);
               MainActivity.setPlace(1);
               MainActivity.setSpinner();
           }
       });
        holder.txtEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCvVisible(true);
                MainActivity.setPos(position);
                MainActivity.setPlace(2);
                MainActivity.setSpinner();
            }
        });
        holder.txtStartPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCvVisible(true);
                MainActivity.setPos(position);
                MainActivity.setPlace(1);
                MainActivity.setSpinner();
            }
        });
        holder.txtEndPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setCvVisible(true);
                MainActivity.setPos(position);
                MainActivity.setPlace(2);
                MainActivity.setSpinner();
            }
        });

        holder.txtTaskAtHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setAkVisible(true);
                MainActivity.setPos(position);
                MainActivity.setDesc();

            }
        });
        holder.txtStartTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doSomething(position);
                return true;
            }
        });
        holder.txtEndTime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doSomething(position);
                return true;
            }
        });
        holder.txtStartPeriod.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doSomething(position);
                return true;
            }
        });
        holder.txtEndPeriod.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doSomething(position);
                return true;
            }
        });
        holder.txtTaskAtHand.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                doSomething(position);
                return true;
            }
        });

        holder.linLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainActivity.setPos(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to delete this task?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(tasks.get(position) != null)
                        tasks.remove(position);
                        notifyDataSetChanged();
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                        boolean b = dataBaseHelper.deleteOne(position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtStartTime;
        private TextView txtEndTime;
        private TextView txtStartPeriod;
        private TextView txtEndPeriod;
        private TextView txtTaskAtHand;
        private View linLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStartTime = itemView.findViewById(R.id.startTime);
            txtEndTime = itemView.findViewById(R.id.endTime);
            txtStartPeriod = itemView.findViewById(R.id.startPeriod);
            txtEndPeriod = itemView.findViewById(R.id.endPeriod);
            txtTaskAtHand = itemView.findViewById(R.id.routineText);
            linLayout = itemView.findViewById(R.id.LinearLayout1);
        }

    }

    public void doSomething(final int posit)
    {
        MainActivity.setPos(posit);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure you want to delete this task?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(tasks.get(posit) != null)
                    tasks.remove(posit);
                notifyDataSetChanged();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                boolean b = dataBaseHelper.deleteOne(posit);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }
}
