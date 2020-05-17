package com.example.lab_3_lists;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CatViewActivity extends AppCompatActivity {

    @Override //Show info about the selected item
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        TextView brand = findViewById(R.id.breed);
        TextView model = findViewById(R.id.size);
        TextView year = findViewById(R.id.lifeDuration);
        TextView description = findViewById(R.id.description);

        String[] data = new String[4];
        data[0] = getIntent().getStringExtra("breed");
        data[1] = getIntent().getStringExtra("size");
        data[2] = getIntent().getStringExtra("lifeDuration");
        data[3] = getIntent().getStringExtra("description");

        brand.setText(data[0]);
        model.setText(data[1]);
        year.setText(data[2]);
        description.setText(data[3]);

        findViewById(R.id.backButton).setOnClickListener(v -> {
            finish();
        });
    }
}


