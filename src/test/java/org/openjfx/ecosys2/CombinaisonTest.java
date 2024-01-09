package org.openjfx.ecosys2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombinaisonTest {

    @Test
    void randomString() {
        Combinaison combinaison = new Combinaison(5);
        String randomString = combinaison.randomString(5);
        assertEquals(5, randomString.length());
    }
}