package org.example;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GearRatiosTest {

    @Test
    public void testSumOfPartNumbers() {
        String machine = "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598..";

        GearRatios ratios = new GearRatios();

        assertEquals(4361, ratios.sumOfPartNumbers(machine));

    }

    @Test
    public void testSumOfPartNumbersBiggerLine() {
// @formatter:off
        String machine =
                ".854...........................................................................362...........271...732........838.........24................\n" +
                "...*.............................117*...........459........767*648....#.........*...................................$...&..=................\n" +
                "....970.........368.124.+............57................653...........723.....366....*443..60.........536....441....45..879.....789...*......\n" +
                "...........749*...-...+..330.....................215%...*................725.....953........%.................*............639......331.419.\n" +
                ".......706.....59.............*....=...262.............678...........857.*............189....................912.+589.846*...*..............";
// @formatter:on
        GearRatios ratios = new GearRatios();

        assertEquals(14927, ratios.sumOfPartNumbers(machine));

    }

    @Test
    public void testIsPartNumber() {
        GearRatios ratios = new GearRatios();
        String previous = null;
        String line = "345";
        String next = "..*.";
        assertTrue(ratios.isPartNumber(previous, line, next));

        previous = "..*.";
        line = "345";
        next = null;
        assertTrue(ratios.isPartNumber(previous, line, next));

        previous = "...";
        line = "345$";
        next = "....";
        assertTrue(ratios.isPartNumber(previous, line, next));

        previous = "...";
        line = "#345";
        next = "....";
        assertTrue(ratios.isPartNumber(previous, line, next));

    }

    @Test
    public void testCheckSymbol() {
        GearRatios ratios = new GearRatios();
        assertTrue(ratios.checkSymbol(".....*....."));
        assertFalse(ratios.checkSymbol(".........."));
    }

    @Test
    public void testCheckPotentialGear() {
        GearRatios ratios = new GearRatios();
        assertEquals(Pair.of(0, 5), ratios.checkPotentialGear(".....*.....", 0, 0));
        assertNull(ratios.checkPotentialGear("..........", 0, 0));
    }

    @Test
    void testisPotentialGear(){
        GearRatios ratios = new GearRatios();
        String previous = null;
        String line = "345";
        String next = "..*.";
        assertEquals(Set.of(Pair.of(1, 2)), ratios.isPotentialGear(previous, line, next, 0, 0));

        previous = "..*.";
        line = "345";
        next = null;
        assertEquals(Set.of(Pair.of(1, 3)), ratios.isPotentialGear(previous, line, next, 2, 1));

        previous = "...";
        line = "345$";
        next = "....";
        assertEquals(Set.of(), ratios.isPotentialGear(previous, line, next, 2, 0));

        previous = "...";
        line = "#345";
        next = "....";
        assertEquals(Set.of(), ratios.isPotentialGear(previous, line, next, 2, 1));
    }

    @Test
    public void testGearRatios() {
        String machine =
                "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...$.*....\n" +
                ".664.598..";

        GearRatios ratios = new GearRatios();

        assertEquals(467835, ratios.gearRatios(machine));

    }

}