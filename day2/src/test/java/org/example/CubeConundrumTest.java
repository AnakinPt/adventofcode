package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeConundrumTest {


    @Test
    void testIsPossible(){
        CubeConundrum cubeConundrum = new CubeConundrum();

        String draw = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertTrue(cubeConundrum.isPossible(draw));
        draw = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertFalse(cubeConundrum.isPossible(draw));
    }

    @Test
    void testGame(){
        CubeConundrum cubeConundrum = new CubeConundrum();

        String draw = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertEquals(1, cubeConundrum.game(draw));
        draw = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertEquals(0, cubeConundrum.game(draw));

    }

    @Test
    void testSumOfIds(){
        CubeConundrum cubeConundrum = new CubeConundrum();

        String games = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

        assertEquals(8, cubeConundrum.sumOfIds(games));
    }

    @Test
    void testPowerOfMinimum(){
        CubeConundrum cubeConundrum = new CubeConundrum();
        String draw = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertEquals(4*2*6, cubeConundrum.powerOfMinimum(draw));
        draw = "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue";
        assertEquals(1*3*4, cubeConundrum.powerOfMinimum(draw));
        draw = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertEquals(20*13*6, cubeConundrum.powerOfMinimum(draw));
        draw = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        assertEquals(14* 3* 15, cubeConundrum.powerOfMinimum(draw));
        draw = "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";
        assertEquals(6* 3* 2, cubeConundrum.powerOfMinimum(draw));
    }

    @Test
    void testPowerOfMinimums(){
        CubeConundrum cubeConundrum = new CubeConundrum();

        String games = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green";

        assertEquals(2286, cubeConundrum.powerOfMinimums(games));
    }

}