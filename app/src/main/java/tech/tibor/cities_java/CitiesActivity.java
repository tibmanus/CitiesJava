package tech.tibor.cities_java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CitiesActivity extends AppCompatActivity {

    CitiesModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        model = new CitiesModel(getResources());
    }
}
