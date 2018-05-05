package tech.tibor.cities_java;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CitiesViewModel extends AndroidViewModel {

    CitiesModel model;

    LiveData<List<City>> cities;

    public CitiesViewModel(@NonNull Application application) {
        super(application);

        model = new CitiesModel(application.getResources());
        cities = model.data;
    }
}
