package com.example.footprint.UI;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;


import com.example.footprint.Model.City;
import com.example.footprint.R;
import com.example.footprint.ViewModel.AddCityViewModel;

import java.util.List;

public class Add extends AppCompatActivity {
        Toolbar toolbar;
        AddCityViewModel addCityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.toolbarOfAdd);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        addCityViewModel = ViewModelProviders.of(this).get(AddCityViewModel.class);
        addCityViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                //update recyclerView
                Toast.makeText(Add.this,"onChanged",Toast.LENGTH_LONG).show();
            }
        });
    }
}