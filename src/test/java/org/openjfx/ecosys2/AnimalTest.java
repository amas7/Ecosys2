package org.openjfx.ecosys2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    //creation of a simple mock concrete class that inherits from Animal to test Animal
    private static class ConcreteAnimal extends Animal {
        public ConcreteAnimal(int maxLife, float pos_x, float pos_y) {
            super(maxLife, pos_x, pos_y);
        }
    }

    @Test
    void testToString() {
        Animal animal = new ConcreteAnimal(50, 1.0f, 2.0f);
        String expectedToString = "Animal{life=50}";
        assertEquals(expectedToString, animal.toString());
    }

    @Test
    void getLife() {
        Animal animal = new ConcreteAnimal(50, 1.0f, 2.0f);
        assertEquals(50, animal.getLife());
    }

    @Test
    void setLife() {
        Animal animal = new ConcreteAnimal(50, 1.0f, 2.0f);
        animal.setLife(30);
        assertEquals(30, animal.getLife());

        // Test that setting life greater than maxLife results in life being set to maxLife
        animal.setLife(60);
        assertEquals(50, animal.getLife());
    }

    @Test
    void getMaxLife() {
        Animal animal = new ConcreteAnimal(50, 1.0f, 2.0f);
        assertEquals(50, animal.getMaxLife());
    }

    @Test
    void setMaxLife() {
        Animal animal = new ConcreteAnimal(50, 1.0f, 2.0f);
        animal.setMaxLife(60);
        assertEquals(60, animal.getMaxLife());
    }


}
