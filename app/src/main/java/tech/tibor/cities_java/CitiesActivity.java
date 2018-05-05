package tech.tibor.cities_java;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        CitiesViewModel viewModel = ViewModelProviders.of(this).get(CitiesViewModel.class);
        viewModel.cities.observe(this, cities -> {
            Log.d("cities", "observer triggered");
        });
    }
}
