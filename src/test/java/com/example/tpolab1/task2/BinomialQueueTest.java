package com.example.tpolab1.task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinomialQueueTest {
    private BinomialQueue bq;
    @BeforeEach
    void setUp() {
        bq = new BinomialQueue();
    }

    @Test
    void testIsEmpty() {
        assertTrue(bq.isEmpty());
    }
    @Test
    void testInsert() throws Overflow {
        bq.insert(new MyInteger(5));
        assertFalse(bq.isEmpty());
        assertEquals(5, ((MyInteger) bq.findMin()).intValue());

        bq.insert(new MyInteger(10));
        assertEquals(5, ((MyInteger) bq.findMin()).intValue());

        bq.insert(new MyInteger(1));
        assertEquals(1, ((MyInteger) bq.findMin()).intValue());
    }

    @Test
    void testFindMin() throws Overflow {
        bq.insert(new MyInteger(5));
        bq.insert(new MyInteger(1));
        bq.insert(new MyInteger(7));

        assertEquals(1, ((MyInteger) bq.findMin()).intValue());
    }

    @Test
    void testDeleteMin() throws Overflow {
        bq.insert(new MyInteger(5));
        bq.insert(new MyInteger(1));
        bq.insert(new MyInteger(7));

        assertEquals(1, ((MyInteger) bq.deleteMin()).intValue());

        assertAll( () -> {
            assertNotEquals(1, ((MyInteger) bq.findMin()).intValue());
            assertEquals(5, ((MyInteger) bq.findMin()).intValue());
        });
    }

    @Test
    void testDeleteMinEqualsFindMin() throws Overflow {
        bq.insert(new MyInteger(5));
        bq.insert(new MyInteger(1));
        bq.insert(new MyInteger(7));

        MyInteger fm = (MyInteger) bq.findMin();
        MyInteger dm = (MyInteger) bq.deleteMin();
        assertEquals(fm, dm);
    }

    @Test
    void testMakeEmpty() throws Overflow {
        assertTrue(bq.isEmpty());

        bq.insert(new MyInteger(5));
        bq.insert(new MyInteger(1));
        bq.insert(new MyInteger(7));

        assertFalse(bq.isEmpty());

        bq.makeEmpty();
        assertTrue(bq.isEmpty());
    }

    @Test
    void testMerge() throws Overflow {
        BinomialQueue bq2 = new BinomialQueue();
        bq.insert(new MyInteger(5));
        bq.insert(new MyInteger(1));
        bq.insert(new MyInteger(7));

        bq2.insert(new MyInteger(-10));
        bq2.insert(new MyInteger(20));
        bq2.insert(new MyInteger(70));

        assertEquals(1, ((MyInteger) bq.findMin()).intValue());
        assertEquals(-10, ((MyInteger) bq2.findMin()).intValue());

        bq.merge(bq2);
        assertEquals(-10, ((MyInteger) bq.findMin()).intValue());
    }

    @Test
    void testIsFull() throws Overflow {
        for (int i = 0; i < ((1 << 14) - 1); ++i) {
            MyInteger x = new MyInteger(i);
            bq.insert(x);
        }
        assertTrue(bq.isFull());
    }

    @Test
    void testOverflow() throws Overflow {
        assertThrows(Overflow.class, () -> {
            for (int i = 0; i < (1 << 14); ++i) {
                MyInteger x = new MyInteger(i);
                bq.insert(x);
            }
        });
    }
}
