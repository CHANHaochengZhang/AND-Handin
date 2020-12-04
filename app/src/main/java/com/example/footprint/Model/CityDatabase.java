package com.example.footprint.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.footprint.UI.Add;

@Database(entities = {City.class}, version = 1)
public abstract class CityDatabase extends RoomDatabase {

    private static CityDatabase instance;

    public abstract CityDao cityDao();

    public static synchronized CityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CityDatabase.class,
                    "city_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();
            Log.e("CityDb","CityDb");
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CityDao cityDao;

        private PopulateDbAsyncTask(CityDatabase db) {
            cityDao = db.cityDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.e("DDDDDDDDDD","INSERT");
            cityDao.insert(new City("Atlanta"));
            cityDao.insert(new City("City1"));
            cityDao.insert(new City("City2"));
            return null;
        }
    }

}
