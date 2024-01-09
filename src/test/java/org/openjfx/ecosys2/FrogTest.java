package org.openjfx.ecosys2;

import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrogTest {

    @Test
    void eat() {
        Frog frog1 = new Frog(0, 0, 0, KeyCode.C);
        Frog frog2 = new Frog(0, 0, 0, KeyCode.G);
        Fly fly = new Fly(4,5,7);


        // Set initial life of prey
        frog2.setLife(10);
        fly.setLife(5);

        frog1.eat(frog2);

        // check if the life value of frog1 has been updated with the life of frog2
        assertEquals(frog1.getMaxLife() + 10, frog1.getLife());

        assertEquals(0, frog2.getLife());

        frog1.eat(fly);
        assertEquals(frog1.getMaxLife() + 10, frog1.getLife());
        assertEquals(0, frog2.getLife());

    }

    @Test
    void testToString() {
        // Create a Frog instance for testing
        Frog frog = new Frog(20.0f, 30.0f, 45.0, KeyCode.UP);

        // Check the string representation
        assertEquals("Frog{life=50\nposition selon x=20.0\nposition selon y=30.0\norientation=45.0}", frog.toString());
    }

    @Test
    void getOrientation() {
        // Create a Frog instance for testing
        Frog frog = new Frog(10.0f, 10.0f, 30.0, KeyCode.RIGHT);

        // Check the initial orientation
        assertEquals(30.0, frog.getOrientation());
    }

    @Test
    void setOrientation() {
        // Create a Frog instance for testing
        Frog frog = new Frog(10.0f, 10.0f, 30.0, KeyCode.RIGHT);

        // Set a new orientation
        frog.setOrientation(60.0);

        // Check if the orientation is updated
        assertEquals(60.0, frog.getOrientation());
    }

    @Test
    void getKeycode() {
        // Create a Frog instance for testing
        Frog frog = new Frog(10.0f, 10.0f, 30.0, KeyCode.RIGHT);

        // Check the initial keycode
        assertEquals(KeyCode.RIGHT, frog.getKeycode());
    }

    @Test
    void setKeycode() {
        // Create a Frog instance for testing
        Frog frog = new Frog(10.0f, 10.0f, 30.0, KeyCode.RIGHT);

        // Set a new keycode
        frog.setKeycode(KeyCode.DOWN);

        // Check if the keycode is updated
        assertEquals(KeyCode.DOWN, frog.getKeycode());
    }
}