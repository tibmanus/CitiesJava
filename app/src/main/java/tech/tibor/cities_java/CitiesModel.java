package tech.tibor.cities_java;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CitiesModel {

    private Resources resources;

    private MutableLiveData<List<City>> data = new MutableLiveData<>();

    public LiveData<List<City>> getData() {
        return data;
    }

    private List<City> parsedData;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    CitiesModel(Resources resources) {
        this(resources, null);
    }

    @VisibleForTesting
    CitiesModel(Resources resources, String json) {
        this.resources = resources;
        new Parser().execute(json);
    }

    class Parser extends AsyncTask<String, Void, List<City>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.postValue(true);
        }

        @Override
        protected List<City> doInBackground(String... strings) {
            String json = strings[0];

            if (json == null) {
                try {
                    InputStream in_s = resources.openRawResource(R.raw.cities);

                    byte[] b = new byte[in_s.available()];
                    in_s.read(b);
                    json = new String(b);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            Type listType = new TypeToken<ArrayList<City>>(){}.getType();
            List<City> list = new Gson().<ArrayList<City>>fromJson(json, listType);
            Collections.sort(list);
            return list;
        }

        @Override
        protected void onPostExecute(List<City> result) {
            super.onPostExecute(result);
            parsedData = result;
            data.setValue(result);
            loading.postValue(false);
        }
    }

    public void filterFor(String query) {
        if (query.trim().isEmpty()) {
            data.setValue(parsedData);
            return;
        }

        new Filter().execute(query);
    }

    class Filter extends AsyncTask<String, Void, List<City>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setValue(true);
        }

        @Override
        protected List<City> doInBackground(String... strings) {
            ArrayList<City> filteredList = new ArrayList<>();
            for (City city : parsedData) {
                if (city.name.length() >= strings[0].length()
                        && city.name.substring(0, strings[0].length()).equals(strings[0])) {
                    filteredList.add(city);
                }
            }

            return filteredList;
        }

        @Override
        protected void onPostExecute(List<City> result) {
            super.onPostExecute(result);
            data.setValue(result);
            loading.setValue(false);
        }
    }
}
