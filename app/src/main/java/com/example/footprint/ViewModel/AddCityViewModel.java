package com.example.footprint.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.footprint.Model.City;
import com.example.footprint.Model.CityRepository;

import java.util.List;

public class AddCityViewModel extends AndroidViewModel {
    private CityRepository repository;
    private LiveData<List<City>> allCities;

    public AddCityViewModel(@NonNull Application application) {
        super(application);
        repository = new CityRepository(application);
        allCities = repository.getAllCities();
    }

    public void insert(City city){
        repository.insert(city);
    }
    public void delete(City city){
        repository.delete(city);
    }

    public void deleteAllNote(){
        repository.deleteAllCities();
    }

    public LiveData<List<City>> getAllCities(){
        return allCities;
    }
}
