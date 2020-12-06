package com.example.footprint.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.footprint.Model.CityRepository;

public class MainViewModel extends AndroidViewModel {
     CityRepository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = CityRepository.getInstance(application);
    }

    public void updatePhoto(String cityname){
        Log.e("MainVM","updatePhoto");
        repository.updatePhoto(cityname);
    }
}
