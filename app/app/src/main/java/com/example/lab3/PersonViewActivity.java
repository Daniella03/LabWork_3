package com.example.lab3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PersonViewActivity extends AppCompatActivity {

    // Showing information of the selected list item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        TextView fio = findViewById(R.id.fio);
        TextView sex = findViewById(R.id.sex);
        TextView age = findViewById(R.id.age);
        TextView description = findViewById(R.id.description);

        String[] data = new String[4];
        data[0] = getIntent().getStringExtra("fio");
        data[1] = getIntent().getStringExtra("sex");
        data[2] = getIntent().getStringExtra("age");
        data[3] = getIntent().getStringExtra("description");

        fio.setText(data[0]);
        sex.setText(data[1]);
        age.setText(data[2]);
        description.setText(data[3]);

        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
        });
    }
}


