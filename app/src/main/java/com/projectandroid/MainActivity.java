package com.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.projectandroid.bean.Employee;
import com.projectandroid.bean.Manager;
import com.projectandroid.bean.Programmer;
import com.projectandroid.bean.Tester;
import com.projectandroid.dao.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Employee> employees = DatabaseHelper.getInstance(this).getAllEmployee();
        for (Employee e : employees) {
            if (e instanceof Manager) {
                Log.d("MYSMG", e.toString());
            } else if (e instanceof Tester) {
                Log.d("MYSMG", e.toString());
            } else {
                Log.d("MYSMG", e.toString());
            }
        }
    }


    //button handler
    public void addEmployee(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}
