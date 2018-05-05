package tech.tibor.cities_java;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class City implements Parcelable, Comparable<City> {

    String _id;

    String country;

    String name;

    Coordinates coord;

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
        return this.name.compareTo(o.name);
        // TODO implement by country after
    }
}
