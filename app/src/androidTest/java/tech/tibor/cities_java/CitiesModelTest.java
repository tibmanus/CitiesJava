package tech.tibor.cities_java;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static tech.tibor.cities_java.TestUtil.*;

@RunWith(AndroidJUnit4.class)
public class CitiesModelTest {

    private CitiesModel citiesModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        citiesModel = new CitiesModel(InstrumentationRegistry.getTargetContext().getResources(),
                json);
    }

    @After
    public void tearDown() {
        citiesModel = null;
    }

    @Test
    public void filterShowsFullAndPartialResults() {
        LiveData<List<City>> cities = citiesModel.getData();
        Observer observer = mock(Observer.class);
        cities.observeForever(observer);
        verify(observer).onChanged(anyList().size() == allCities().size());
        citiesModel.filterFor("Bud");
        verify(observer).onChanged(anyList().containsAll(matchesForBud()));
    }

    @Test
    public void filterForEmptyShowsAll() {
        LiveData<List<City>> cities = citiesModel.getData();
        Observer observer = mock(Observer.class);
        cities.observeForever(observer);
        verify(observer).onChanged(anyList().size() == allCities().size());
        citiesModel.filterFor("  ");
        verify(observer).onChanged(anyList().size() == allCities().size());
    }

    @Test
    public void filterForPartialMatchWithinShowsNoResults() {
        LiveData<List<City>> cities = citiesModel.getData();
        Observer observer = mock(Observer.class);
        cities.observeForever(observer);
        verify(observer).onChanged(anyList().size() == allCities().size());
        citiesModel.filterFor("uda");
        verify(observer).onChanged(anyList().size() == 0);
    }
}
