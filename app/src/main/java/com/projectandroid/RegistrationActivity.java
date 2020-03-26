package com.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.projectandroid.bean.Car;
import com.projectandroid.bean.Employee;
import com.projectandroid.bean.Manager;
import com.projectandroid.bean.Motorcycle;
import com.projectandroid.bean.Programmer;
import com.projectandroid.bean.Tester;
import com.projectandroid.bean.Vehicle;
import com.projectandroid.dao.DatabaseHelper;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    TableRow trCharacter, trSideCar, trCarType;
    TextView tvCharacter;
    RadioGroup rgVehicle, rgSideCar;
    EditText etFname, etLname, etBirthYear, etMonthlySalary, etOccupationRate, etEmpID, etVehicleModel, etPlateNumber, etCharacter, etCarType;
    String empType, color, vehicle;
    Spinner spinnerColor, spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //inflating views
        trCharacter = findViewById(R.id.trCharacter);
        trSideCar = findViewById(R.id.trSideCar);
        trCarType = findViewById(R.id.trCarType);
        tvCharacter = findViewById(R.id.tvCharacter);
        rgVehicle = findViewById(R.id.rgVehicle);
        rgSideCar = findViewById(R.id.rgSideCar);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etBirthYear = findViewById(R.id.etBirthYear);
        etMonthlySalary = findViewById(R.id.etMonthlySalary);
        etOccupationRate = findViewById(R.id.etOccupationRate);
        etEmpID = findViewById(R.id.etID);
        etVehicleModel = findViewById(R.id.etVehicleModel);
        etPlateNumber = findViewById(R.id.etPlateNumber);
        etCharacter = findViewById(R.id.etCharacter);
        etCarType = findViewById(R.id.etCarType);
        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
                R.array.employee_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerType.setAdapter(adapterType);

        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constants.COLOR_NAMES);
        // Specify the layout to use when the list of choices appears
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerColor.setAdapter(adapterColor);

        // change vehicle type options according to option selected
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                empType = item;
                switch (item) {
                    case "Choose a type":
                        trCharacter.setVisibility(View.GONE);
                        break;
                    case "Manager":
                        trCharacter.setVisibility(View.VISIBLE);
                        tvCharacter.setText("# clients");
                        break;
                    case "Tester":
                        trCharacter.setVisibility(View.VISIBLE);
                        tvCharacter.setText("# bugs");
                        break;
                    case "Programmer":
                        trCharacter.setVisibility(View.VISIBLE);
                        tvCharacter.setText("# projects");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                color = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rgVehicle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbBike) {
                    vehicle = "MotorBike";
                    trSideCar.setVisibility(View.VISIBLE);
                    trCarType.setVisibility(View.GONE);
                } else if (i == R.id.rbCar) {
                    vehicle = "Car";
                    trSideCar.setVisibility(View.GONE);
                    trCarType.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void registerEmployee(View view) {

        // validations for input fields
        String errMsg = "";
        if (etFname.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify first name";
            etFname.requestFocus();
            etFname.setError(errMsg);
            showError(errMsg);
            return;
        }

        if (etLname.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify last name";
            etLname.requestFocus();
            etLname.setError(errMsg);
            showError(errMsg);
            return;
        }

        if (etBirthYear.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify year of birth";
            etBirthYear.requestFocus();
            etBirthYear.setError(errMsg);
            showError(errMsg);
            return;
        }

        int birthYear = convertToInteger(etBirthYear.getText().toString().trim());

        if (birthYear <= 1990 || birthYear > Calendar.getInstance().get(Calendar.YEAR)) {
            errMsg = "Please specify year of birth between 1990 and " + (Calendar.getInstance().get(Calendar.YEAR) + 1);
            etBirthYear.requestFocus();
            etBirthYear.setError(errMsg);
            showError(errMsg);
            return;
        }

        if (etEmpID.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify employee id";
            etEmpID.requestFocus();
            etEmpID.setError(errMsg);
            showError(errMsg);
            return;
        }

        int empID = convertToInteger(etEmpID.getText().toString().trim());

        // check for duplicate emp id
        if (DatabaseHelper.getInstance(this).employeeExist(empID)) {
            errMsg = "Please specify unique employee id";
            etEmpID.requestFocus();
            etEmpID.setError(errMsg);
            showError(errMsg);
            return;
        }
        if (empType.equalsIgnoreCase("choose a type")) {
            errMsg = "Please specify employee type";
            showError(errMsg);
            return;
        }
        if (etCharacter.getText().toString().trim().isEmpty()) {
            switch (empType) {
                case "Manager":
                    errMsg = "Please specify number of clients";
                    break;
                case "Tester":
                    errMsg = "Please specify number of bugs";
                    break;
                case "Programmer":
                    errMsg = "Please specify number of projects";
                    break;
            }
            showError(errMsg);
            etCharacter.requestFocus();
            etCharacter.setError(errMsg);
            return;
        }

        if (vehicle == null) {
            errMsg = "Please select a vehicle";
            showError(errMsg);
            return;
        }

        if (vehicle.equalsIgnoreCase("motorbike")) {
            int id = rgSideCar.getCheckedRadioButtonId();
            if (id != R.id.rbSideCarYes && id != R.id.rbSideCarNo) {
                errMsg = "Please specify your bike has a sidecar or not";
                showError(errMsg);
                return;
            }
        } else if (etCarType.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify car type";
            etCarType.requestFocus();
            etCarType.setError(errMsg);
            showError(errMsg);
            return;
        }


        if (etVehicleModel.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify vehicle model";
            etVehicleModel.requestFocus();
            etVehicleModel.setError(errMsg);
            showError(errMsg);
            return;
        }


        if (etPlateNumber.getText().toString().trim().isEmpty()) {
            errMsg = "Please specify plate number";
            etPlateNumber.requestFocus();
            etPlateNumber.setError(errMsg);
            showError(errMsg);
            return;
        }


        if (color.equalsIgnoreCase("choose a color")) {
            errMsg = "Please specify vehicle color";
            showError(errMsg);
            return;
        }
        Vehicle v;
        if (vehicle.equalsIgnoreCase("car")) {
            v = new Car(etVehicleModel.getText().toString().trim(), etPlateNumber.getText().toString().trim(), color, etCarType.getText().toString().trim());
        } else {
            boolean sideCar = false;
            if (rgSideCar.getCheckedRadioButtonId() == R.id.rbSideCarYes) {
                sideCar = true;
            } else {
                sideCar = false;
            }
            v = new Motorcycle(etVehicleModel.getText().toString().trim(), etPlateNumber.getText().toString().trim(), color, sideCar);
        }

        double income = Double.parseDouble(etMonthlySalary.getText().toString().trim());

        // setting occupation rate
        int rate = Integer.parseInt(etOccupationRate.getText().toString().trim());
        if (rate < 10) {
            rate = 10;
        }

        if (rate > 100) {
            rate = 100;
        }
        boolean res = false;
        int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(etBirthYear.getText().toString().trim());
        switch (empType) {
            case "Manager":
                Manager m = new Manager(etFname.getText().toString().trim() + " " + etLname.getText().toString().trim(), etEmpID.getText().toString().trim(), age, income, rate, v, Integer.parseInt(etCharacter.getText().toString().trim()));
                m.setAnnualIncome();
                res = DatabaseHelper.getInstance(this).insertEmployee(m);
                break;
            case "Tester":
                Tester t = new Tester(etFname.getText().toString().trim() + " " + etLname.getText().toString().trim(), etEmpID.getText().toString().trim(), age, income, rate, v, Integer.parseInt(etCharacter.getText().toString().trim()));
                t.setAnnualIncome();
                res = DatabaseHelper.getInstance(this).insertEmployee(t);
                break;
            case "Programmer":
                Programmer p = new Programmer(etFname.getText().toString().trim() + " " + etLname.getText().toString().trim(), etEmpID.getText().toString().trim(), age, income, rate, v, Integer.parseInt(etCharacter.getText().toString().trim()));
                p.setAnnualIncome();
                res = DatabaseHelper.getInstance(this).insertEmployee(p);
                break;
        }
        if (res) {
            //move to main activity in case of success
            finish();
        } else {
            showError("Unable to insert employee");
        }
    }


    public void showError(String msg) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    public int convertToInteger(String val) {
        return Integer.parseInt(val);
    }
}
