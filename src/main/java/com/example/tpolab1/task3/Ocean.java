package com.example.tpolab1.task3;

import java.util.HashSet;
import java.util.Set;

public class Ocean {
    private final String name;
    private final Set<Archipelago> archipelagos;

    public Ocean(String name) {
        this.name = name;
        this.archipelagos = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Set<Archipelago> getArchipelagos() {
        return archipelagos;
    }

    public void addArchipelago(Archipelago a) {
        archipelagos.add(a);
    }

    public void removeArchipelago(Archipelago a) {
        archipelagos.remove(a);
    }
}
