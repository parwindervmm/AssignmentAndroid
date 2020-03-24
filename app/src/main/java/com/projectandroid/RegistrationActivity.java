package com.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;
import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    TableRow trCharacter, trSideCar;
    TextView tvCharacter;
    RadioGroup rgVehicle;
    EditText etFname, etLname, etBirthYear, etMonthlySalary, etOccupationRate, etEmpID, etVehicleModel, etPlateNumber;
    String empType, color;
    Spinner spinnerColor, spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //inflating views
        trCharacter = findViewById(R.id.trCharacter);
        trSideCar = findViewById(R.id.trSideCar);
        tvCharacter = findViewById(R.id.tvCharacter);
        rgVehicle = findViewById(R.id.rgVehicle);
        etFname = findViewById(R.id.etFname);
        etLname = findViewById(R.id.etLname);
        etBirthYear = findViewById(R.id.etBirthYear);
        etMonthlySalary = findViewById(R.id.etMonthlySalary);
        etOccupationRate = findViewById(R.id.etOccupationRate);
        etEmpID = findViewById(R.id.etID);
        etVehicleModel = findViewById(R.id.etVehicleModel);
        etPlateNumber = findViewById(R.id.etPlateNumber);
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
        ArrayAdapter<String> adapterColor = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Colors.COLOR_NAMES);
        // Specify the layout to use when the list of choices appears
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerColor.setAdapter(adapterColor);

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
                    trSideCar.setVisibility(View.VISIBLE);
                } else if (i == R.id.rbCar) {
                    trSideCar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void registerEmployee(View view) {
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

        if (checkDuplicateEmployee(empID)) {
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
        if (empType.equalsIgnoreCase("choose a type")) {
            errMsg = "Please specify employee type";
            showError(errMsg);
            return;
        }


    }

    private boolean checkDuplicateEmployee(int empID) {
        return false;
    }


    public void showError(String msg) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show();
    }

    public int convertToInteger(String val) {
        return Integer.parseInt(val);
    }
}
