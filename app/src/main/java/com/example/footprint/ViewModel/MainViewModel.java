package com.example.footprint.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.footprint.Model.City;
import com.example.footprint.Model.CityRepository;
import com.example.footprint.Model.Photo;

import java.security.Policy;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    CityRepository repository;
    private LiveData<List<City>> allCities;
    private LiveData<Photo> photo;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = CityRepository.getInstance(application);
    }

    public LiveData<List<City>> getCities() {
        allCities = repository.getAllCities();
        return allCities;
    }

   public LiveData<List<Photo>> getPhoto(){
        return repository.getPhoto();
    }

    public void updatePhoto(String cityName){
        repository.updatePhoto(cityName);
    }




}
