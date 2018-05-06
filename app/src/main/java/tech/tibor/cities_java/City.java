package tech.tibor.cities_java;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class City implements Parcelable, Comparable<City> {

    String _id;

    String country;

    String name;

    Coordinates coord;

    City(String _id, String country, String name, Coordinates coord) {
        this._id = _id;
        this.country = country;
        this.name = name;
        this.coord = coord;
    }

    protected City(Parcel in) {
        _id = in.readString();
        country = in.readString();
        name = in.readString();
        coord = in.readParcelable(Coordinates.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(country);
        dest.writeString(name);
        dest.writeParcelable(coord, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int compareTo(@NonNull City o) {
        int value = this.name.compareTo(o.name);
        if (value == 0) {
            return this.country.compareTo(o.country);
        } else {
            return value;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof City) {
            City o = (City) other;
            return _id.equals(o._id)
                    && name.equals(o.name)
                    && country.equals(o.country)
                    && coord.equals(o.coord);
        } else return false;
    }
}
