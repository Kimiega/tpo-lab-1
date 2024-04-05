package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Planet {
    private final String name;
    private final Set<Ocean> oceans;

    public Planet(String name) {
        this.name = name;
        this.oceans = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Ocean> getOceans() {
        return oceans;
    }

    public void addOcean(Ocean o) {
        oceans.add(o);
    }

    public void removeOcean(Ocean o) {
        oceans.remove(o);
    }
}
