package com.example.lab_3_lists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Data input
        String[][] data = new String[7][4];
        data[0][0] = "Бенгальская кошка"; data[0][1] = "большой"; data[0][2] = "12-15 лет"; data[0][3] = "Хотя внешний вид бенгальских кошек очень напоминает дикий, отзывы владельцев о характере своих любимцев всегда говорят об обратном";
        data[1][0] = "Абиссинская кошка"; data[1][1] = "средний"; data[1][2] = "12-15 лет"; data[1][3] = "Дикая внешность абиссинской кошки очень обманчива, характер у кошки уравновешенный и очень покладистый";
        data[2][0] = "Сиамская кошка"; data[2][1] = "средний"; data[2][2] = "до 30 лет"; data[2][3] = "Верные и преданные сиамки обожают своих хозяев, часто следуют за ними по пятам и разговаривают, используя целую палитру различных звуков";
        data[3][0] = "Бурманская кошка"; data[3][1] = "средний"; data[3][2] = "12-15 лет"; data[3][3] = "Энергичный темперамент кошек позволяет им сохранять озорное и подвижное поведение котят даже во взрослом возрасте";
        data[4][0] = "Сибирская кошка"; data[4][1] = "средний"; data[4][2] = "12-15 лет"; data[4][3] = "Сибирская кошка отличается своим пушистым красивым мехом. Это достаточно крупное, мощное и сильное животное";
        data[5][0] = "Американский керл"; data[5][1] = "средний"; data[5][2] = "до 20 лет"; data[5][3] = "Американский керл – это уникальная кошка, а уникальна она своей необычной формой ушей, уши у этих кошек загнутые!";
        data[6][0] = "Тайская кошка"; data[6][1] = "средний"; data[6][2] = "до 20 лет"; data[6][3] = "Внешние черты и характеристики тайской кошки: это сильные животные, компактных размеров, форма головы круглая, уши маленькие.";

        //Adapter
        MyAdapter adapter = new MyAdapter(this, data);

        ListView list = findViewById(R.id.list);

        //Adapter object -> list widget
        list.setAdapter(adapter);

        //Selection of the list item
        list.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListViewActivity.this, CatViewActivity.class);
            intent.putExtra("breed", data[position][0]);
            intent.putExtra("size", data[position][1]);
            intent.putExtra("lifeDuration", data[position][2]);
            intent.putExtra("description", data[position][3]);
            startActivity(intent);
        });

        //Switching to RecyclerView
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
                convertView = inflater.inflate(R.layout.activity_item, parent, false); //Layout -> object tree

            //Object tree widgets
            TextView breed = convertView.findViewById(R.id.breed);
            TextView size = convertView.findViewById(R.id.size);
            TextView lifeDuration = convertView.findViewById(R.id.lifeDuration);
            TextView description = convertView.findViewById(R.id.description);

            //Change the info about widgets
            breed.setText(data[position][0]);
            size.setText(data[position][1]);
            lifeDuration.setText(data[position][2]);
            description.setText(data[position][3]);

            return convertView;
        }
    }
}


