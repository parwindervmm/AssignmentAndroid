package com.projectandroid.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.projectandroid.DetailActivity;
import com.projectandroid.R;
import com.projectandroid.RegistrationActivity;
import com.projectandroid.bean.Employee;

import java.util.ArrayList;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private final Actions actions;
    private final Context ctx;
    private ArrayList<Employee> al;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public EmployeeAdapter(Context ctx, ArrayList<Employee> al, Actions actions) {
        this.al = al;
        this.ctx = ctx;
        viewBinderHelper.setOpenOnlyOne(true);
        this.actions = actions;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(ctx).inflate(R.layout.emp_single_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee emp = al.get(position);
        viewBinderHelper.bind(holder.swipeRevealLayout, emp.getId());
        holder.tvEmpData.setText(String.format("Name:  %s\nId:  %s", emp.getName(), emp.getId()));
        holder.tvEmpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("data", emp.toString());
                ctx.startActivity(intent);
            }
        });
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx)
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to delete employee?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                actions.deleteEmployee(Integer.parseInt(emp.getId()));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                builder.create();
                builder.show();
            }
        });
        holder.edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ctx, RegistrationActivity.class);
                i.putExtra("emp", emp);
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public void updateList(ArrayList<Employee> al) {
        this.al = al;
        notifyDataSetChanged();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView tvEmpData;
        SwipeRevealLayout swipeRevealLayout;
        ImageView edit_icon, delete_icon;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpData = itemView.findViewById(R.id.tvEmpData);
            swipeRevealLayout = itemView.findViewById(R.id.swl);
            edit_icon = itemView.findViewById(R.id.edit_icon);
            delete_icon = itemView.findViewById(R.id.delete_icon);
        }
    }
}
