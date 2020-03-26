package com.projectandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.projectandroid.DetailActivity;
import com.projectandroid.R;
import com.projectandroid.bean.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    Context ctx;
    ArrayList<Employee> al;

    public EmployeeAdapter(Context ctx, ArrayList<Employee> al) {
        this.al = al;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(ctx).inflate(R.layout.emp_single_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        final Employee emp = al.get(position);
        Log.d("MYMSG", "Hi");
        holder.tvEmpData.setText(String.format("Name:  %s\nId:  %s", emp.getName(), emp.getId()));
        holder.tvEmpData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, DetailActivity.class);
                intent.putExtra("data", emp.toString());
                ctx.startActivity(intent);
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

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmpData = itemView.findViewById(R.id.tvEmpData);
        }
    }
}
