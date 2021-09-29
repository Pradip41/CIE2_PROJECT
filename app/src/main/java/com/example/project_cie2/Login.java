package com.example.project_cie2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    SharedPreferences.Editor editor;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DatabaseHelper(this);
    }

    public void login(View view) {
        EditText edt = findViewById(R.id.edtUsername);
        EditText edt1 = findViewById(R.id.edtPassword);

        String s1 = edt.getText().toString();
        String s2 = edt1.getText().toString();

        Cursor cursor = databaseHelper.selectData();
        if(cursor.getCount() > 0 && cursor != null){
            cursor.moveToFirst();
            do {
                String v1 = cursor.getString(1);
                String v2 = cursor.getString(2);
            }while (cursor.moveToNext());
        }

        if(!s1.isEmpty() && !s2.isEmpty()) {
            Toast.makeText(this, "Not Blank", Toast.LENGTH_SHORT).show();
            if (databaseHelper.checkUser(edt.getText().toString(), edt1.getText().toString())) {
                Intent a1 = new Intent(Login.this, MainActivity.class);
                a1.putExtra("uname", s1);
                startActivity(a1);
                Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "please enter valid details", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void fun(View view) {
        Intent a2 = new Intent(this,Register.class);
        startActivity(a2);
    }
}


