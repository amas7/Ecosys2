package org.openjfx.ecosys2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntrudorTest {

    @Test
    void eat() {
        Intrudor intrudor = new Intrudor(10);


        Fly fly = new Fly(5,8,7);

        int initialLife = fly.getLife();

        intrudor.eat(fly);

        assertEquals(0, fly.getLife());
    }




}