package org.openjfx.ecosys2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementTest {
    //creation of a mock class that inherits from Element to test  the abstract class Element
    private static class ConcreteElement extends Element {
        public ConcreteElement(float pos_x, float pos_y) {
            super(pos_x, pos_y);
        }
    }

    @Test
    void getPos_x() {
        // Create an Element instance for testing
        Element element = new ConcreteElement(10.0f, 20.0f);

        // Check the initial x position
        assertEquals(10.0f, element.getPos_x());
    }

    @Test
    void setPos_x() {
        // Create an Element instance for testing
        Element element = new ConcreteElement(10.0f, 20.0f);

        // Set a new x position
        element.setPos_x(30.0f);

        // Check if the x position is updated
        assertEquals(30.0f, element.getPos_x());
    }

    @Test
    void getPos_y() {
        // Create an Element instance for testing
        Element element = new ConcreteElement(10.0f, 20.0f);

        // Check the initial y position
        assertEquals(20.0f, element.getPos_y());
    }

    @Test
    void setPos_y() {
        // Create an Element instance for testing
        Element element = new ConcreteElement(10.0f, 20.0f);

        // Set a new y position
        element.setPos_y(40.0f);

        // Check if the y position is updated
        assertEquals(40.0f, element.getPos_y());
    }

}