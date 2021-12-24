package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page_property extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_property);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void GoToOwner(View view) {
        Intent intent = new Intent(this,page_owner.class);
        startActivity(intent);

    }

    public void GoToMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}