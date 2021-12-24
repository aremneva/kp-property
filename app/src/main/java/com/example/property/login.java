package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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



    }

    public void GoToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String tName = eName.getEditText().getText().toString();
        String tPassword = ePassword.getEditText().getText().toString();
        DBHelper myDB = new DBHelper(this);
        SQLiteDatabase sDB = myDB.getWritableDatabase();
        //Log.d("MAIN","agPas: "+myDB.againstPassword(tName,tPassword));

        if (myDB.getCountUser(tName)>0){
        if(myDB.againstPassword(tName,tPassword)) startActivity(intent);
        else Toast.makeText(this,"Неправильный пароль",Toast.LENGTH_SHORT).show();
        myDB.againstPassword(tName,tPassword);
        }
        else Toast.makeText(this,"Несуществующий логин",Toast.LENGTH_SHORT).show();
        sDB.close();
        myDB.close();

    }

    public void GoToReg(View view) {
        Intent intent= new Intent(this,register.class);
        startActivity(intent);
    }

}