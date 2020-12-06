package com.example.footprint.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.footprint.Model.City;
import com.example.footprint.Model.Photo;
import com.example.footprint.R;
import com.example.footprint.ViewModel.MainViewModel;

import java.util.List;

public class SettingActivity extends AppCompatActivity {
    ImageView imageView;
    MainViewModel viewModel;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        imageView = findViewById(R.id.imageViewSetting);
        editText = findViewById(R.id.editTextTextCitySetting);

        Log.e("Setting", "Photo1");
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getPhoto().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                Glide.with(SettingActivity.this).load(photos.get(0).getUrls()).into(imageView);
                Log.e("Setting", "Photo2");
            }
        });
    }
    public void loadHorsens(View view) {
        viewModel.updatePhoto("Horsens");
    }

    public void loadNanjing(View view) {
        viewModel.updatePhoto("南京");
    }

    public void loadNY(View view) {
        viewModel.updatePhoto("NewYork");
    }

    public void searchButton(View view) {
        viewModel.updatePhoto(editText.getText().toString());
    }
}