package com.example.asii;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    Spinner qis1;
    Spinner qis2;
    Spinner qis3;
    TextView hidden;
    private Animation top, bottom;

    private RecyclerView recyclerView;
    private DataItemAdapter adapter;
    private List<DataItem> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spiner);
        qis1 = findViewById(R.id.quistion1);
        qis2 = findViewById(R.id.quistion2);
        qis3 = findViewById(R.id.quistion3);
        hidden = findViewById(R.id.hidden);
        ImageView img = findViewById(R.id.img);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        img.setAnimation(bottom);
        spinner.setAnimation(top);

        setupRecyclerView();
        putData();

        loadDataFromSharedPreferences();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataItems = new ArrayList<>();
        adapter = new DataItemAdapter(dataItems);
        recyclerView.setAdapter(adapter);
    }

    private void putData() {
        Subjects sub = new Subjects();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sub.finddata());
        spinner.setAdapter(arrayAdapter);
    }

    public void Result(View view) {
        String st1 = qis1.getSelectedItem().toString();
        String st2 = qis2.getSelectedItem().toString();
        String st3 = qis3.getSelectedItem().toString();
        hidden.setVisibility(View.VISIBLE);
        int counter = 0;
        if (st1.equals("1948")) {
            counter++;
        }
        if (st2.equals("1967")) {
            counter++;
        }
        if (st3.equals("2000")) {
            counter++;
        }
        if (counter == 3) {
            hidden.setText("The result is: " + counter + " you are a hero!!");
        } else {
            hidden.setText("Q1 answer: 1948\nQ2 answer: 1967\nQ3 answer: 2000\nThe result is: " + counter);
        }

        DataItem dataItem = new DataItem(st1, st2, st3, counter);
        addDataItem(dataItem);
        saveDataToSharedPreferences();
    }

    private void loadDataFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        String dataJson = sharedPreferences.getString("data_list", "");
        Gson gson = new Gson();
        Type dataType = new TypeToken<List<DataItem>>() {}.getType();
        List<DataItem> savedDataItems = gson.fromJson(dataJson, dataType);
        if (savedDataItems != null) {
            dataItems.addAll(savedDataItems);
            adapter.notifyDataSetChanged();
        }
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String dataJson = gson.toJson(dataItems);
        editor.putString("data_list", dataJson);
        editor.apply();
    }

    private void addDataItem(DataItem item) {
        dataItems.add(item);
        adapter.notifyItemInserted(dataItems.size() - 1);
    }
}