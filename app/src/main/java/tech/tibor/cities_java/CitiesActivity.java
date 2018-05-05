package tech.tibor.cities_java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CitiesActivity
        extends AppCompatActivity
    implements CitiesFragment.OnListFragmentInteractionListener {

    public static final String EXTRA_MESSAGE = "tibor.tech.City";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
    }

    @Override
    public void onListFragmentInteraction(City item) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(EXTRA_MESSAGE, item);
        startActivity(intent);
    }
}
