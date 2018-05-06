package tech.tibor.cities_java;

import java.util.Arrays;
import java.util.List;

public class TestUtil {

    static String json = "[\n" +
            "{\"country\":\"HU\",\"name\":\"Budakalasz\",\"_id\":3054651,\"coord\":{\"lon\":19.049999,\"lat\":47.616669}},\n" +
            "{\"country\":\"US\",\"name\":\"Bud\",\"_id\":4255151,\"coord\":{\"lon\":-86.175819,\"lat\":39.446991}},\n" +
            "{\"country\":\"IN\",\"name\":\"Sriperumbudur\",\"_id\":1255630,\"coord\":{\"lon\":79.948891,\"lat\":12.96889}}\n" +
            "]";

    static City Budakalasz = new City("3054651",
            "Budakalasz",
            "HU",
            new Coordinates(19.049999, 47.616669)
    );
    static City Bud = new City("4255151",
            "Bud",
            "USA",
            new Coordinates(-86.175819, 39.446991));

    static City Sriperumbudur = new City("1255630",
            "IN",
            "Sriperumbudur",
            new Coordinates(79.948891,12.96889));

    static List<City> matchesForBud() {
        return Arrays.asList(Budakalasz, Bud);
    }

    static List<City> allCities() {
        return Arrays.asList(Bud, Budakalasz, Sriperumbudur);
    }
}
