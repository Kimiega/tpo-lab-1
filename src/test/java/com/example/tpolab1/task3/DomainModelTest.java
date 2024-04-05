package com.example.tpolab1.task3;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainModelTest {

    @Test
    void testHuman() {
        Human human = new Human("Igor");
        assertEquals("Igor", human.getName());
    }

    @Test
    void testPlace() {
        Place place = new Place("Дом", Size.EXTRA_LARGE);
        assertEquals("Дом", place.getName());
        assertEquals(Size.EXTRA_LARGE, place.getSize());

        Human h1 = new Human("Kostya");
        Human h2 = new Human("Kirill");
        place.addHuman(h1);
        place.addHuman(h2);
        assertEquals(Set.of(h1, h2), place.getHumans());

        place.removeHuman(h1);
        assertEquals(Set.of(h2), place.getHumans());
    }
    @Test
    void testIsland() {
        Island island = new Island("Бирмуды", Size.MEDIUM);
        assertEquals("Бирмуды", island.getName());
        assertEquals(Size.MEDIUM, island.getSize());

        Place p1 = new Place("Дом", Size.EXTRA_LARGE);
        Place p2 = new Place("Дом2", Size.LITTLE);

        island.addPlace(p1);
        island.addPlace(p2);
        assertEquals(Set.of(p1, p2), island.getPlaces());

        island.removePlace(p1);
        assertEquals(Set.of(p2), island.getPlaces());
    }

    @Test
    void testArchipelago() {
        Archipelago archipelago = new Archipelago("Гулаг", Size.LARGE);
        assertEquals("Гулаг", archipelago.getName());
        assertEquals(Size.LARGE, archipelago.getSize());

        Island i1 = new Island("Остров сокровищ", Size.SMALL);
        Island i2 = new Island("Остров невезения", Size.MEDIUM);

        archipelago.addIslands(i1);
        archipelago.addIslands(i2);
        assertEquals(Set.of(i1, i2), archipelago.getIslands());

        archipelago.removeIsland(i1);
        assertEquals(Set.of(i2), archipelago.getIslands());
    }

    @Test
    void testOcean() {
        Ocean ocean = new Ocean("Южный");
        assertEquals("Южный", ocean.getName());

        Archipelago a1 = new Archipelago("Гулаг", Size.LARGE);
        Archipelago a2 = new Archipelago("Звёздочка", Size.LITTLE);


        ocean.addArchipelago(a1);
        ocean.addArchipelago(a2);
        assertEquals(Set.of(a1, a2), ocean.getArchipelagos());

        ocean.removeArchipelago(a1);
        assertEquals(Set.of(a2), ocean.getArchipelagos());
    }

    @Test
    void testPlanet() {
        Planet planet = new Planet("Земля");
        assertEquals("Земля", planet.getName());

        Ocean o1 = new Ocean("Южный");
        Ocean o2 = new Ocean("Северо-Ледовитый");


        planet.addOcean(o1);
        planet.addOcean(o2);
        assertEquals(Set.of(o1, o2), planet.getOceans());

        planet.removeOcean(o1);
        assertEquals(Set.of(o2), planet.getOceans());
    }

    @Test
    void testTransport() {
        Place p1 = new Place("Точка А", Size.EXTRA_LARGE);
        Place p2 = new Place("Точка Б", Size.LITTLE);
        Transport transport = new Transport("Тесла Х", MovingType.MOVE, p1);
        assertEquals("Тесла Х", transport.getName());
        assertEquals(p1, transport.getPlace());
        assertEquals(MovingType.MOVE, transport.getMovingType());
        assertEquals(emptySet(), transport.getPassengers());

        Human h1 = new Human("Vasya");
        transport.addPassenger(h1);
        assertEquals(Set.of(h1), transport.getPassengers());

        transport.move(p2);
        assertEquals(p2, transport.getPlace());

        transport.changeMovingType(MovingType.FLY);
        assertEquals(MovingType.FLY, transport.getMovingType());

        transport.removePassenger(h1);
        assertEquals(emptySet(), transport.getPassengers());
    }

    @Test
    void testGalaxyDict() {
        GalaxyDict dict = new GalaxyDict();
        dict.addWord("Hello", "Привет");
        dict.addWord("Bye-bye", "Пока");
        assertEquals("Привет", dict.getMeaning("Hello"));
        assertEquals("Пока", dict.getMeaning("Bye-bye"));

        dict.addWord("Hello", "Здравия желаю");
        assertEquals("Здравия желаю", dict.getMeaning("Hello"));
    }

    @Test
    void testUsualFlow() {
        Planet p = new Planet("Земля 2");
        Ocean o = new Ocean("Пруд");
        Archipelago a = new Archipelago("Гулаг", Size.SOMEWHAT_USEFUL);
        Island i1 = new Island("Пасхи", Size.SMALL);
        Island i2 = new Island("Франция", Size.MEDIUM);
        Place p1 = new Place("Космический порт", Size.LITTLE);
        Place p2 = new Place("Площадка", Size.SMALL);
        Human h = new Human("Зафод Библброкс");
        Transport t1 = new Transport("Золотое Сердце", MovingType.FLY, p2);
        Transport t2 = new Transport("Катер", MovingType.SLIDE, p1);

        GalaxyDict dict = new GalaxyDict();

        dict.addWord("пасхи", "маленький, ровный и светло-коричневый");

        p.addOcean(o);
        o.addArchipelago(a);
        a.addIslands(i1);
        a.addIslands(i2);
        i1.addPlace(p1);
        i2.addPlace(p2);
        p1.addHuman(h);

        assertAll(() -> {
           assertEquals(Set.of(o), p.getOceans());
           assertEquals(Set.of(a), o.getArchipelagos());
           assertEquals(Set.of(i1, i2), a.getIslands());
           assertEquals(Set.of(p1), i1.getPlaces());
           assertEquals(Set.of(p2), i2.getPlaces());
           assertEquals(Set.of(h), p1.getHumans());
           assertEquals(emptySet(), p2.getHumans());
           assertEquals(emptySet(), t1.getPassengers());
           assertEquals(p2, t1.getPlace());
           assertEquals(p1, t2.getPlace());
        });

        p1.removeHuman(h);
        t2.addPassenger(h);
        assertAll(() -> {
            assertEquals(emptySet(), p1.getHumans());
            assertEquals(Set.of(h), t2.getPassengers());
            assertEquals(p1, t2.getPlace());
        });

        t2.move(p2);
        assertAll(() -> {
            assertEquals(p2, t2.getPlace());
        });
    }
}
