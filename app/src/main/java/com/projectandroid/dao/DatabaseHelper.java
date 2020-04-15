package com.projectandroid.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.projectandroid.bean.Car;
import com.projectandroid.bean.Employee;
import com.projectandroid.bean.Manager;
import com.projectandroid.bean.Motorcycle;
import com.projectandroid.bean.Programmer;
import com.projectandroid.bean.Tester;
import com.projectandroid.bean.Vehicle;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper mInstance = null;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DatabaseHelper getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext(), "MYDB", null, 1);
        }
        return mInstance;
    }


    public boolean employeeExist(int eid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query("employee", null, "eid = ?", new String[]{String.valueOf(eid)}, null, null, null);
        if (res.getCount() > 0) {
            return true;
        }

        return false;
    }

    public boolean deleteEmployee(int eid) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete("employee", "eid = " + eid + "", null) > 0;
    }

    public boolean insertEmployee(Employee emp) {
        Manager m = null;
        Programmer p = null;
        Tester t = null;

        if (emp instanceof Manager) {
            m = (Manager) emp;
        } else if (emp instanceof Tester) {
            t = (Tester) emp;
        } else {
            p = (Programmer) emp;
        }

        Vehicle vehicle = emp.getVehicle();

        Car c = null;
        Motorcycle motorcycle = null;
        if (vehicle instanceof Car) {
            c = (Car) vehicle;
        } else {
            motorcycle = (Motorcycle) vehicle;
        }

        // insert emp object into db
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", emp.getName());
        contentValues.put("age", emp.getAge());
        contentValues.put("income", emp.getIncome());
        contentValues.put("occupation", emp.getRate());
        contentValues.put("eid", emp.getId());
        contentValues.put("etype", m != null ? "Manager" : (t != null ? "Tester" : "Programmer"));
        contentValues.put("enumber", m != null ? m.getNbClients() : (t != null ? t.getNbBugs() : p.getNbProjects()));
        contentValues.put("vehicle", c != null ? "Car" : "MotorBike");
        contentValues.put("type", c != null ? c.getType() : String.valueOf(motorcycle.isSidecar()));
        contentValues.put("model", vehicle.getMake());
        contentValues.put("plate", vehicle.getPlate());
        contentValues.put("color", vehicle.getColor());
        long res = db.insert("employee", null, contentValues);
        if (res == -1) {
            return false;
        }
        return true;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table with columns
        sqLiteDatabase.execSQL(
                "create table IF NOT EXISTS employee " +
                        "(id integer primary key, name text,age text,income text, occupation text,eid text,etype text,enumber text,vehicle text,type text,model text,plate text,color text)"
        );
    }

    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> emp_list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from employee", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            Employee employee = null;
            Vehicle v = null;
            String empName = res.getString(res.getColumnIndex("name"));
            String empAge = res.getString(res.getColumnIndex("age"));
            String income = res.getString(res.getColumnIndex("income"));
            String rate = res.getString(res.getColumnIndex("occupation"));
            String id = res.getString(res.getColumnIndex("eid"));
            String empType = res.getString(res.getColumnIndex("etype"));
            String enumber = res.getString(res.getColumnIndex("enumber"));
            String vehicle = res.getString(res.getColumnIndex("vehicle"));
            String type = res.getString(res.getColumnIndex("type"));
            String model = res.getString(res.getColumnIndex("model"));
            String plate = res.getString(res.getColumnIndex("plate"));
            String color = res.getString(res.getColumnIndex("color"));
            if (vehicle.equalsIgnoreCase("car")) {
                v = new Car(model, plate, color, type);
            } else {
                v = new Motorcycle(model, plate, color, Boolean.parseBoolean(type));
            }
            switch (empType) {
                case "Manager":
                    employee = new Manager(empName, id, Integer.parseInt(empAge), Double.parseDouble(income), Double.parseDouble(rate), v, Integer.parseInt(enumber));
                    break;
                case "Tester":
                    employee = new Tester(empName, id, Integer.parseInt(empAge), Double.parseDouble(income), Double.parseDouble(rate), v, Integer.parseInt(enumber));
                    break;
                case "Programmer":
                    employee = new Programmer(empName, id, Integer.parseInt(empAge), Double.parseDouble(income), Double.parseDouble(rate), v, Integer.parseInt(enumber));
                    break;
            }
            emp_list.add(employee);
            res.moveToNext();
        }
        return emp_list;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS employee");
        onCreate(sqLiteDatabase);
    }

}
