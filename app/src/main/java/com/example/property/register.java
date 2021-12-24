package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class register extends AppCompatActivity {
        TextInputLayout eName;
        TextInputLayout ePassword;
        TextInputLayout rePassword;
        TextInputLayout eEmail;
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
        eEmail=findViewById(R.id.eEmail);
        rePassword=findViewById(R.id.eRePassword);

        String login = eName.getEditText().getText().toString();
        String password = ePassword.getEditText().getText().toString();
        String email = eEmail.getEditText().getText().toString();
        String repas=rePassword.getEditText().getText().toString();

        if (!password.equals(repas)){
            Toast.makeText(this,"Пароли не совпадают",Toast.LENGTH_SHORT).show();
        }
        else {
            if (db.getCountUser(login) == 0) {
                db.addUser(login, password, email);
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Такой пользователь уже есть ", Toast.LENGTH_SHORT).show();
            }
        }
        db.close();

    }

    public void Login(View view) {
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
}