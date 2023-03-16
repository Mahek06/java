package com.sumit.mycrud;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.sumit.mycrud.model.Employee;

import java.io.IOException;

public class FormActivity extends AppCompatActivity {

    EditText name,salary,password,dob;
    Button update;

    RadioButton male,female;
    RadioGroup gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Intent intent = getIntent();
        Employee employee = (Employee) intent.getSerializableExtra("Employee");

        name = findViewById(R.id.ename);
        salary = findViewById(R.id.esalary);
        password = findViewById(R.id.password);
        dob = findViewById(R.id.dob);
        update = findViewById(R.id.save);
        gender = findViewById(R.id.egender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);


        name.setText(employee.getEmp_name());
        salary.setText(String.valueOf(employee.getSalary()));
        password.setText(employee.getEmp_password());
        dob.setText(employee.getEmp_dob());
        if(employee.getEmp_gender().equals("male")){
            male.setChecked(true);
        }else{
            female.setChecked(true);
        }

        update.setOnClickListener(view -> {
            employee.setEmp_dob(dob.getText().toString());
            employee.setEmp_gender(male.isChecked()?"male":"female");
            employee.setEmp_name(name.getText().toString());
            employee.setEmp_password(password.getText().toString());
            employee.setSalary(Double.valueOf(salary.getText().toString()));
            employee.setEmp_dob(dob.getText().toString());
            ApiCalls apiCalls = new ApiCalls();
            try {
                apiCalls.callapi("update",FormActivity.this,0,employee);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}