package com.example.footprint.Model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.footprint.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityRepository {

    private static CityRepository instance;

    private CityDao cityDao;
    private LiveData<List<City>> allCities;

    private ArrayList<City> dummyCity;

    private MutableLiveData<List<Photo>> photos;


    private CityRepository(Application application) {
        CityDatabase database = CityDatabase.getInstance(application);
        cityDao = database.cityDao();
        allCities = cityDao.getAllCities();

        photos = new MutableLiveData<>();


        dummyCity = new ArrayList<>();
        dummyCity.add(new City("Horsens"));
        dummyCity.add(new City("Nanjing"));
        dummyCity.add(new City("Newyork"));

    }

    public static synchronized CityRepository getInstance(Application application) {
        if (instance == null) {
            instance = new CityRepository(application);
        }
        return instance;
    }

    /*Room database start*/
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
    /*Room database end*/


    /*Photo Api Unsplash start */
    public LiveData<List<Photo>> getPhoto() {
        return photos;
    }

    public void updatePhoto(String cityName) {
        PhotoApi photoApi = ServiceGenerator.getPhotoApi();
        Call<PhotoResponse> call = photoApi.getPhotos(cityName);
        call.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                if (response.code() == 200) {
                   photos.setValue(response.body().getResults());
                } else {
                    Log.e("!! Photo url is notOK", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {
                Log.e("Retrofit", "Wrong");
            }
        });
    }
}
