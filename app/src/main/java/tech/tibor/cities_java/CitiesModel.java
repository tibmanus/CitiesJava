package tech.tibor.cities_java;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
        this.resources = resources;
        new Parser().execute();
    }

    class Parser extends AsyncTask<Void, Void, List<City>> {

        @Override
        protected List<City> doInBackground(Void... voids) {

            loading.postValue(true);

            String json = null;
            try {

                InputStream in_s = resources.openRawResource(R.raw.cities);

                byte[] b = new byte[in_s.available()];
                in_s.read(b);
                json = new String(b);
            } catch (Exception e) {
                 e.printStackTrace();
                 return null;
            }
            Type listType = new TypeToken<ArrayList<City>>(){}.getType();
            return new Gson().<ArrayList<City>>fromJson(json, listType);
        }

        @Override
        protected void onPostExecute(List<City> result) {
            super.onPostExecute(result);
            parsedData = result;
            data.setValue(result);
            loading.postValue(false);
        }
    }
}
