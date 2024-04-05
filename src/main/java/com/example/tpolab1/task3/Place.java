package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Place {
    private final String name;
    private final Set<Human> humans;
    private final Size size;

    public Place(String name, Size size) {
        this.name = name;
        this.humans = new HashSet<>();
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Set<Human> getHumans() {
        return humans;
    }

    public void addHuman(Human a) {
        humans.add(a);
    }

    public void removeHuman(Human a) {
        humans.remove(a);
    }

    public Size getSize() {
        return size;
    }
}
