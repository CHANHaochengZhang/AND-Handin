package com.example.footprint.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CityRepository {
    private CityDao cityDao;
    private LiveData<List<City>> allCities;

    public CityRepository(Application application) {
        CityDatabase database = CityDatabase.getInstance(application);
        cityDao = database.cityDao();
        allCities = cityDao.getAllCities();
        Log.e("DDDDDDDDDD","Rep");
    }
// Seems like this way is outdated - working in the background
    public void insert(City city) {
        new InsertCityAsyncTask(cityDao).execute(city);
    }

    public void update(City city) {
        new UpdateCityAsyncTask(cityDao).execute(city);
    }

    public void delete(City city) {
        new DeleteCityAsyncTask(cityDao).execute(city);

    }

    public void deleteAllCities() {
        new DeleteAllCityAsyncTask(cityDao).execute();
    }

    public LiveData<List<City>> getAllCities() {
        return allCities;
    }

    private static class InsertCityAsyncTask extends AsyncTask<City, Void, Void> {
        private CityDao cityDao;

        private InsertCityAsyncTask(CityDao cityDao) {
            this.cityDao = cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            cityDao.insert(cities[0]);
            return null;
        }
    }

    private static class UpdateCityAsyncTask extends AsyncTask<City, Void, Void> {
        private CityDao cityDao;

        private UpdateCityAsyncTask(CityDao cityDao) {
            this.cityDao = cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            cityDao.update(cities[0]);
            return null;
        }
    }

    private static class DeleteCityAsyncTask extends AsyncTask<City, Void, Void> {
        private CityDao cityDao;

        private DeleteCityAsyncTask(CityDao cityDao) {
            this.cityDao = cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            cityDao.delete(cities[0]);
            return null;
        }
    }

    private static class DeleteAllCityAsyncTask extends AsyncTask<Void, Void, Void> {
        private CityDao cityDao;

        private DeleteAllCityAsyncTask(CityDao cityDao) {
            this.cityDao = cityDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cityDao.deleteAllCities();
            return null;
        }
    }
}
