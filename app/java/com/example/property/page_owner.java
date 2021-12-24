package com.example.property;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page_owner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_owner);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void GoToPage(View view) {
        Intent intent = new Intent(this,page_property.class);
        startActivity(intent);
    }
}