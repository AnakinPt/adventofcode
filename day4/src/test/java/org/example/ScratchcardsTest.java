package org.example;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ScratchcardsTest {

    @Test
    void testCheckPrizes(){
        Scratchcards scratchcards = new Scratchcards();
        assertEquals(13, scratchcards.checkPrizes(cards));
    }

    @Test
    void testCheckPrize(){
        Scratchcards scratchcards = new Scratchcards();

        Set<Integer> winningNumbers = Set.of(41, 48, 83, 86, 17);
        Set<Integer> gameNumbers = Set.of(83, 86, 6, 31, 17, 9, 48, 53);

        assertEquals(8, scratchcards.checkPrize(winningNumbers, gameNumbers));

        winningNumbers = Set.of(13,32, 20, 16, 61);
        gameNumbers = Set.of(61, 30, 68, 82, 17, 32, 24, 19);
        assertEquals(2, scratchcards.checkPrize(winningNumbers, gameNumbers));

        winningNumbers = Set.of(41, 92, 73, 84, 69);
        gameNumbers = Set.of(59, 84, 76, 51, 58,  5, 54, 83);
        assertEquals(1, scratchcards.checkPrize(winningNumbers, gameNumbers));

        winningNumbers = Set.of(87, 83, 26, 28, 32);
        gameNumbers = Set.of(88, 30, 70, 12, 93, 22, 82, 36);
        assertEquals(0, scratchcards.checkPrize(winningNumbers, gameNumbers));
    }

    @Test
    void testCheckPrizeFromCard(){
        Scratchcards scratchcards = new Scratchcards();

        String card = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53";
        assertEquals(8, scratchcards.checkPrize(card));
    }

    @Test
    void testBowlingPrizes(){
        Scratchcards scratchcards = new Scratchcards();
        assertEquals(30, scratchcards.bowlingPrizes(cards));
    }

    static final String cards = "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11";
}