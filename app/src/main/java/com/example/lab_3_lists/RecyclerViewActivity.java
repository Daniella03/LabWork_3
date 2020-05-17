package com.example.lab_3_lists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        String[][] data = null; // get data from the ListViewActivity
        Object[] objectArray = (Object[]) Objects.requireNonNull(getIntent().getExtras()).getSerializable("data");
        if (objectArray != null){
            data = new String[objectArray.length][];
            for (int i = 0; i < objectArray.length; i++)
                data[i]=(String[]) objectArray[i];
        }

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        //Passing an adapter object to list widget
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(new MyAdapter(this, data));

        //Switch to ListView
        findViewById(R.id.switchButton).setOnClickListener(v -> {
            Intent intent = new Intent(RecyclerViewActivity.this, ListViewActivity.class);
            startActivity(intent);
            finish();
        });
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.CatViewHolder> {
        String[][] data;

        MyAdapter(Context context, String[][] data) {
            this.data = data;
        }

        //Fill the layout for every RecyclerView item
        @NonNull
        @Override
        public CatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);
            return new CatViewHolder(v);
        }

        //Define the info about every item from a RecyclerView
        @Override
        public void onBindViewHolder(@NonNull CatViewHolder CatViewHolder, int i) {
            CatViewHolder.breed.setText(data[i][0]);
            CatViewHolder.size.setText(data[i][1]);
            CatViewHolder.lifeDuration.setText(data[i][2]);
            CatViewHolder.description.setText(data[i][3]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        static class CatViewHolder extends RecyclerView.ViewHolder {
            TextView breed, size, lifeDuration, description;

            CatViewHolder(@NonNull final View itemView) {
                super(itemView);
                breed = itemView.findViewById(R.id.breed);
                size = itemView.findViewById(R.id.size);
                lifeDuration = itemView.findViewById(R.id.lifeDuration);
                description = itemView.findViewById(R.id.description);

                // List items
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), CatViewActivity.class);
                    intent.putExtra("breed", breed.getText().toString());
                    intent.putExtra("size", size.getText().toString());
                    intent.putExtra("lifeDuration", lifeDuration.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    v.getContext().startActivity(intent);
                });
            }
        }
    }
}


