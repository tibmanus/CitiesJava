package tech.tibor.cities_java;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordinates implements Parcelable {

    Double lon;

    Double lat;

    Coordinates(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    protected Coordinates(Parcel in) {
        if (in.readByte() == 0) {
            lon = null;
        } else {
            lon = in.readDouble();
        }
        if (in.readByte() == 0) {
            lat = null;
        } else {
            lat = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (lon == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lon);
        }
        if (lat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lat);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Coordinates> CREATOR = new Creator<Coordinates>() {
        @Override
        public Coordinates createFromParcel(Parcel in) {
            return new Coordinates(in);
        }

        @Override
        public Coordinates[] newArray(int size) {
            return new Coordinates[size];
        }
    };

    @Override
    public boolean equals(Object other) {
        if (other instanceof Coordinates) {
            Coordinates o = (Coordinates) other;
            return lat.equals(o.lat) && lon.equals(o.lon);
        } else return false;
    }
}