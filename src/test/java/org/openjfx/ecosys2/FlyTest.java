package org.openjfx.ecosys2;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlyTest {

    @Test
    void update_nearest() {
        //creation of a simple ecosystem with 1 fly and 2 frogs
        Ecosystem ecosystem = new Ecosystem(200,200,2,1,0);
        Fly fly = ecosystem.getTabFly().getFirst();
        Frog frog1 = ecosystem.getTabFrog().getFirst();
        Frog frog2 = ecosystem.getTabFrog().getLast();

        //set the position of the fly and the frogs
        fly.setPos_x(50);
        fly.setPos_y(50);
        frog1.setPos_x(51);
        frog1.setPos_y(51);

        frog2.setPos_x(0);
        frog2.setPos_y(0);

        // Update
        fly.update_nearest(ecosystem, 20.0f);

        // Check that the nearest frog is frog1 to the ecosystem
        assertEquals(frog1, fly.getNearest_frog());

        frog1.setPos_x(0);
        frog1.setPos_y(0);
        fly.update_nearest(ecosystem, 2f);
        // Check that the nearest frog is null il all the frogs are too far
        assertNull(fly.getNearest_frog());

    }

    @Test
    void move() {
        // Create a Fly instance for testing
        Fly fly = new Fly(50.0f, 50.0f, 5);

        float WINDOW_WIDTH = 100.0f;
        float WINDOW_HEIGHT = 100.0f;

        // Perform the move operation
        fly.move(WINDOW_WIDTH, WINDOW_HEIGHT);
        fly.move(WINDOW_WIDTH, WINDOW_HEIGHT);
        fly.move(WINDOW_WIDTH, WINDOW_HEIGHT);
        fly.move(WINDOW_WIDTH, WINDOW_HEIGHT);
        fly.move(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Check if the fly moves
        assertTrue(fly.getPos_x() != 50.0f);
        assertTrue(fly.getPos_y() != 50.0f);


        // Check that the fly's position is within the window boundaries
        assertTrue(fly.getPos_x() >= 0 && fly.getPos_x() <= WINDOW_WIDTH);
        assertTrue(fly.getPos_y() >= 0 && fly.getPos_y() <= WINDOW_HEIGHT);
    }

    @Test
    void testToString() {
        // Create a Fly instance for testing
        Fly fly = new Fly(20.0f, 30.0f, 8);

        // Check the string representation
        assertEquals("Fly{life=3\nposition selon x=20.0\nposition selon y=30.0\nflying speed=8}", fly.toString());
    }

    @Test
    void getF_speed() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Check the initial flying speed
        assertEquals(5, fly.getF_speed());
    }

    @Test
    void setF_speed() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Set a new flying speed
        fly.setF_speed(8);

        // Check if the flying speed is updated
        assertEquals(8, fly.getF_speed());
    }

    @Test
    void getAge() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Check the initial age (should be 0 by default)
        assertEquals(0, fly.getAge());
    }

    @Test
    void setAge() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Set a new age
        fly.setAge(2);

        // Check if the age is updated
        assertEquals(2, fly.getAge());
    }

    @Test
    void getNearest_frog() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Check the initial nearest frog (should be null by default)
        assertNull(fly.getNearest_frog());
    }

    @Test
    void setNearest_frog() {
        // Create a Fly instance for testing
        Fly fly = new Fly(10.0f, 10.0f, 5);

        // Create a Frog for testing
        Frog frog = new Frog(15.0f, 15.0f,4, KeyCode.C);

        // Set the nearest frog
        fly.setNearest_frog(frog);

        // Check if the nearest frog is updated
        assertEquals(frog, fly.getNearest_frog());
    }
}