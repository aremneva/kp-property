package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class login extends AppCompatActivity {
    TextInputLayout eName;
    TextInputLayout ePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        eName= (TextInputLayout)  findViewById(R.id.eName);
        ePassword= (TextInputLayout) findViewById(R.id.ePassword);

        String tName = eName.getEditText().getText().toString();
        String tPassword = ePassword.getEditText().getText().toString();

    }

    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);

        DBHelper myDB = new DBHelper(this);
        SQLiteDatabase sDB = myDB.getWritableDatabase();
        sDB.close();
        myDB.close();
        startActivity(intent);
    }

    public void GoToReg(View view) {
        Intent intent= new Intent(this,register.class);
        startActivity(intent);
    }

}