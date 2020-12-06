package com.example.footprint.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.footprint.Model.City;
import com.example.footprint.R;
import com.example.footprint.ViewModel.AddCityViewModel;

import java.util.List;

public class Add extends AppCompatActivity {
    Toolbar toolbar;
    AddCityViewModel addCityViewModel;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        toolbar = findViewById(R.id.toolbarOfAdd);
        editText = findViewById(R.id.editTextCityName);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//添加默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        RecyclerView recyclerView = findViewById(R.id.recycler_add_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        AddAdapter adapter = new AddAdapter();
        recyclerView.setAdapter(adapter);

        addCityViewModel = ViewModelProviders.of(this).get(AddCityViewModel.class);
        addCityViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                //update recyclerView
                adapter.setCityNames(cities);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                addCityViewModel.delete(adapter.getCityAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Add.this,"Deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);
    }

    public void onClickInsert(View view) {

        String cityName = editText.getText().toString().trim();
        if (!cityName.isEmpty()) {
            addCityViewModel.insert(new City(cityName));
        }

    }
}