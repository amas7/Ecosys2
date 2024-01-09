package org.openjfx.ecosys2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EcosystemTest {

    @Test
    void testToString() {
        // Create an Ecosystem instance for testing
        Ecosystem ecosystem = new Ecosystem(500, 500, 3, 2, 1);
        ecosystem.setMonth("January"); // Set a specific month for testing

        // Define the expected string representation
        String expectedToString = "Ecosys{" +
                "nb_frog=3, " +
                "nb_fly=2, " +
                "tabFly=" + ecosystem.getTabFly() + ", " +
                "tabFrog=" + ecosystem.getTabFrog() + ", " +
                "tabIntru=" + ecosystem.getTabIntru() + ", " +
                "length=500.0, " +
                "height=500.0, " +
                "month=January" +
                '}';

        // Check if the actual toString matches the expected string
        assertEquals(expectedToString, ecosystem.toString());
    }


}