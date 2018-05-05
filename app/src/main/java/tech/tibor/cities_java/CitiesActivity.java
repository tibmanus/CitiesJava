package tech.tibor.cities_java;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CitiesActivity
        extends AppCompatActivity
    implements CitiesFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
    }

    @Override
    public void onListFragmentInteraction(City item) {

    }
}
