package com.projectandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.projectandroid.adapter.Actions;
import com.projectandroid.adapter.EmployeeAdapter;


import com.projectandroid.bean.Employee;
import com.projectandroid.dao.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Actions {

    RecyclerView rv;
    ArrayList<Employee> employees = new ArrayList<>();
    EmployeeAdapter employeeAdapter;
    EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSearch = findViewById(R.id.etSearchEmp);
        //search listener
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // filter your list from your input
                filter(s.toString());
            }
        });
        rv = findViewById(R.id.rv);

        employeeAdapter = new EmployeeAdapter(this, employees, this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(employeeAdapter);

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param

        // divider for recycler items
        // set adapter to recycler view

    }

    void filter(String text) {
        ArrayList<Employee> filterList = new ArrayList<>();
        if (text.trim().isEmpty()) {
            filterList = employees;
        } else {
            for (Employee e : employees) {
                // make a case in-sensitive search
                if (e.getName().toLowerCase().contains(text.trim().toLowerCase())) {
                    filterList.add(e);
                }
            }
        }
        //call adapter to update list
        employeeAdapter.updateList(filterList);
    }


    // method to populate data into recycler view
    public void getData() {
        employees.clear();
        ArrayList<Employee> allEmployee = DatabaseHelper.getInstance(this).getAllEmployee();
        employees.addAll(allEmployee);
        employeeAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    //button handler
    public void addEmployee(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    @Override
    public void deleteEmployee(int eid) {
        boolean b = DatabaseHelper.getInstance(this).deleteEmployee(eid);
        if (b) {
            getData();
        }
    }
}
