package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Island {
    private final String name;
    private final Set<Place> places;
    private final Size size;

    public Island(String name, Size size) {
        this.name = name;
        this.places = new HashSet<>();
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void addPlace(Place a) {
        places.add(a);
    }

    public void removePlace(Place a) {
        places.remove(a);
    }

    public Size getSize() {
        return size;
    }
}
