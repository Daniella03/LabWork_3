package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Data generation
        String[][] data = new String[17][4];
        data[0][0] = "Mike Cross"; data[0][1] = "Male"; data[0][2] = "23"; data[0][3] = "Hardworking and reliable employee";
        data[1][0] = "Christina Curry"; data[1][1] = "Female"; data[1][2] = "27"; data[1][3] = "Demanded specialist";
        data[2][0] = "Solomon Downs"; data[2][1] = "Male"; data[2][2] = "28"; data[2][3] = "Senior employee";
        data[3][0] = "Clinton Graves"; data[3][1] = "Male"; data[3][2] = "37"; data[3][3] = "Demanded specialist";
        data[4][0] = "Maggie Whitehead"; data[4][1] = "Female"; data[4][2] = "25"; data[4][3] = "Hardworking and reliable employee";
        data[5][0] = "Brett Sanford"; data[5][1] = "Male"; data[5][2] = "42"; data[5][3] = "Senior employee";
        data[6][0] = "Roman Simmons"; data[6][1] = "Male"; data[6][2] = "39"; data[6][3] = "Demanded specialist";
        data[7][0] = "Ashly Martin"; data[7][1] = "Female"; data[7][2] = "35"; data[7][3] = "Hardworking and reliable employee";
        data[8][0] = "Kingston Melton"; data[8][1] = "Male"; data[8][2] = "50"; data[8][3] = "Demanded specialist";
        data[9][0] = "Immanuel Castillo"; data[9][1] = "Male"; data[9][2] = "26"; data[9][3] = "Senior employee";
        data[10][0] = "Bella Osborn"; data[10][1] = "Female"; data[10][2] = "35"; data[10][3] = "Hardworking and reliable employee";
        data[11][0] = "Joseph Jarvis"; data[11][1] = "Male"; data[11][2] = "44"; data[11][3] = "Senior employee";
        data[12][0] = "Adeline Koch"; data[12][1] = "Female"; data[12][2] = "47"; data[12][3] = "Hardworking and reliable employee";
        data[13][0] = "Bryan West"; data[13][1] = "Male"; data[13][2] = "33"; data[13][3] = "Demanded specialist";
        data[14][0] = "Robert Graves"; data[14][1] = "Male"; data[14][2] = "29"; data[14][3] = "Senior employee";
        data[15][0] = "Will Solis"; data[15][1] = "Male"; data[15][2] = "28"; data[15][3] = "Senior employee";
        data[16][0] = "Rina Holloway"; data[16][1] = "Female"; data[16][2] = "30"; data[16][3] = "Hardworking and reliable employee";

        // Creating an adapter object
        MyAdapter adapter = new MyAdapter(this, data);

        ListView list = findViewById(R.id.list);

        // Passing an adapter object to a list widget
        list.setAdapter(adapter);

        // List item selection
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListViewActivity.this, PersonViewActivity.class);
            intent.putExtra("fio", data[position][0]);
            intent.putExtra("sex", data[position][1]);
            intent.putExtra("age", data[position][2]);
            intent.putExtra("description", data[position][3]);
            startActivity(intent);
        });

        // Switching to RecyclerView list
        findViewById(R.id.switchButton).setOnClickListener(v -> {
            Intent intent = new Intent(ListViewActivity.this, RecyclerViewActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("data",  data);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        });
    }

    public static class MyAdapter extends BaseAdapter {
        private String[][] data;
        private LayoutInflater inflater;

        MyAdapter(Context context, String[][] data) {
            this.data = data;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = inflater.inflate(R.layout.activity_item, parent, false); // Converting layout to object tree

            // Getting access to the object tree widgets
            TextView fio = convertView.findViewById(R.id.fio);
            TextView sex = convertView.findViewById(R.id.sex);
            TextView age = convertView.findViewById(R.id.age);
            TextView description = convertView.findViewById(R.id.description);

            // Changing the contents of widgets
            fio.setText(data[position][0]);
            sex.setText(data[position][1]);
            age.setText(data[position][2]);
            description.setText(data[position][3]);

            // Returning of the modified tree of objects
            return convertView;
        }
    }
}


