package tech.tibor.cities_java;

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

    private List<City> parsedData;


    CitiesModel(Resources resources) {
        this.resources = resources;
        new Parser().execute();
    }

    class Parser extends AsyncTask<Void, Void, List<City>> {

        @Override
        protected List<City> doInBackground(Void... voids) {


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
        }
    }
}
