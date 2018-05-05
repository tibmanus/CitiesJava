package tech.tibor.cities_java;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CitiesViewModel extends AndroidViewModel {

    private CitiesModel model;

    public CitiesModel getModel() {
        return model;
    }

    private LiveData<List<City>> cities;

    public LiveData<List<City>> getCities() {
        return cities;
    }

    private LiveData<Boolean> loading;

    public LiveData<Boolean> getLoading() {
        return loading;
    }


    public CitiesViewModel(@NonNull Application application) {
        super(application);

        model = new CitiesModel(application.getResources());
        cities = model.getData();
        loading = model.getLoading();
    }
}
