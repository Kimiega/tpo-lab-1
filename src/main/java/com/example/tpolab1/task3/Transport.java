package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Transport {
    private final String name;
    private MovingType movingType;
    private Place place;
    private final Set<Human> passengers;

    public Transport(String name, MovingType movingType, Place place) {
        this.name = name;
        this.movingType = movingType;
        this.place = place;
        this.passengers = new HashSet<>();
    }
    public void move(Place newPlace) {
        place = newPlace;
    };

    public void changeMovingType(MovingType newMovingType) {
        movingType = newMovingType;
    }

    public String getName() {
        return name;
    }

    public MovingType getMovingType() {
        return movingType;
    }

    public Place getPlace() {
        return place;
    }

    public void addPassenger(Human human) {
        passengers.add(human);
    }

    public void removePassenger(Human human) {
        passengers.remove(human);
    }

    public Set<Human> getPassengers() {
        return passengers;
    }
}
