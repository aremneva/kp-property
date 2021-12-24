package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class register extends AppCompatActivity {
        TextInputLayout eName;
        TextInputLayout ePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }

    public void GoToLog(View view) {
        DBHelper db = new DBHelper(this);
        Cursor c;

        eName=findViewById(R.id.eName);
        ePassword=findViewById(R.id.ePassword);

        String login = eName.getEditText().getText().toString();
        String password = ePassword.getEditText().getText().toString();

        db.addUser(login,password,null,null,null,null);
        db.getUser(login);
        db.close();
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
}