package com.example.project_cie2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
    }

    public void rgstr(View view) {
        EditText e1 = findViewById(R.id.edt1);
        EditText e2 = findViewById(R.id.edt2);

        String valUsername, valPassword;
        valUsername = e1.getText().toString();
        valPassword = e2.getText().toString();

        if(databaseHelper.insertData(valUsername,valPassword)){
            Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }

        Intent a1 = new Intent(this,Login.class);
        startActivity(a1);
        finish();

    }
}