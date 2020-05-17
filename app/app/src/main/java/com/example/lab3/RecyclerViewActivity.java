package com.example.lab3;

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

        // Getting data from the ListViewActivity
        String[][] data = null;
        Object[] objectArray = (Object[]) Objects.requireNonNull(getIntent().getExtras()).getSerializable("data");
        if (objectArray != null){
            data = new String[objectArray.length][];
            for (int i = 0; i < objectArray.length; i++)
                data[i]=(String[]) objectArray[i];
        }

        RecyclerView rv = findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        // Configuring RecyclerView with the layout manager and passing an adapter object to a list widget
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));
        rv.setAdapter(new MyAdapter(this, data));

        // Switching to ListView list
        findViewById(R.id.switchButton).setOnClickListener(v -> {
            Intent intent = new Intent(RecyclerViewActivity.this, ListViewActivity.class);
            startActivity(intent);
            finish();
        });
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.PersonViewHolder> {
        String[][] data;

        MyAdapter(Context context, String[][] data) {
            this.data = data;
        }

        // Specifying and filling out the layout for each RecyclerView element
        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);
            return new PersonViewHolder(v);
        }

        // Defining the contents of each item from a RecyclerView
        @Override
        public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int i) {
            personViewHolder.fio.setText(data[i][0]);
            personViewHolder.sex.setText(data[i][1]);
            personViewHolder.age.setText(data[i][2]);
            personViewHolder.description.setText(data[i][3]);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        static class PersonViewHolder extends RecyclerView.ViewHolder {
            TextView fio, sex, age, description;

            PersonViewHolder(@NonNull final View itemView) {
                super(itemView);
                fio = itemView.findViewById(R.id.fio);
                sex = itemView.findViewById(R.id.sex);
                age = itemView.findViewById(R.id.age);
                description = itemView.findViewById(R.id.description);

                // List item selection
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), PersonViewActivity.class);
                    intent.putExtra("fio", fio.getText().toString());
                    intent.putExtra("sex", sex.getText().toString());
                    intent.putExtra("age", age.getText().toString());
                    intent.putExtra("description", description.getText().toString());
                    v.getContext().startActivity(intent);
                });
            }
        }
    }
}


