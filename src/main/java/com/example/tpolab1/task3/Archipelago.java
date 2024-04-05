package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Archipelago {
    private final String name;
    private final Set<Island> islands;
    private final Size size;

    public Archipelago(String name, Size size) {
        this.name = name;
        this.islands = new HashSet<>();
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Set<Island> getIslands() {
        return islands;
    }

    public void addIslands(Island a) {
        islands.add(a);
    }

    public void removeIsland(Island a) {
        islands.remove(a);
    }

    public Size getSize() {
        return size;
    }
}
