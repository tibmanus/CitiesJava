package tech.tibor.cities_java;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class CitiesViewModel extends AndroidViewModel {

    private CitiesModel mModel;

    private LiveData<List<City>> mCities;

    public LiveData<List<City>> getCities() {
        return mCities;
    }

    private LiveData<Boolean> mLoading;

    public LiveData<Boolean> getLoading() {
        return mLoading;
    }

    private String mSearchQuery = "";

    public void setSearchQuery(String query) {
        mSearchQuery = query;
        mModel.filterFor(query);
    }

    public String getSearchQuery() {
        return mSearchQuery;
    }


    public CitiesViewModel(@NonNull Application application) {
        super(application);
        mModel = new CitiesModel(application.getResources());
        mCities = mModel.getData();
        mLoading = mModel.getLoading();
    }
}
