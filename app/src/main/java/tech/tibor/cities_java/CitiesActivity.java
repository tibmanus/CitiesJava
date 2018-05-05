package tech.tibor.cities_java;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import tech.tibor.cities_java.dummy.DummyContent;

public class CitiesActivity
        extends AppCompatActivity
    implements CitiesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        CitiesViewModel viewModel = ViewModelProviders.of(this).get(CitiesViewModel.class);
        viewModel.getCities().observe(this, cities -> {
            Log.d("cities", "observer triggered");
        });

        viewModel.getLoading().observe(this, loading -> {
            Log.d("loading", "observer triggered");
        });
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
