package com.example.footprint.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {City.class}, version = 1)
public abstract class CityDatabase extends RoomDatabase {

    private static CityDatabase instance;

    public abstract CityDao cityDao();

    public static synchronized CityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CityDatabase.class,
                    "city_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private CityDao cityDao;

        private PopulateDbAsyncTask(CityDatabase db) {
            cityDao = db.cityDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cityDao.insert(new City("Atlanta"));
            return null;
        }
    }

}
