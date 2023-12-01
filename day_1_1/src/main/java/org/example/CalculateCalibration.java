package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateCalibration {
    public int calculate(String text) {
        return Arrays.stream(text.split("\\n")).mapToInt(this::calculateLine).sum();
    }

    public int calculateWithLettersToo(String text) {
        return Arrays.stream(text.split("\\n")).mapToInt(this::calculateLineWithLettersToo).sum();
    }

    private int calculateLine(String text) {
        Matcher matcher = Pattern.compile("^[a-z]*(\\d)").matcher(text);
        matcher.find();
        int firstInt = Integer.parseInt(matcher.group(1));
        matcher = Pattern.compile("(\\d)[a-z]*$").matcher(text);
        matcher.find();
        int lastInt = Integer.parseInt(matcher.group(1));
        return firstInt * 10 + lastInt;
    }

    private int calculateLineWithLettersToo(String text) {
        Matcher matcher = Pattern.compile("(one|two|three|four|five|six|seven|eight|nine|\\d)").matcher(text);
        matcher.find();
        int firstInt = stringToInt(matcher.group());
        String reverse = new StringBuilder("one|two|three|four|five|six|seven|eight|nine").reverse().toString();
        Matcher matcherLast=Pattern.compile("("+reverse+"|\\d)").matcher(new StringBuilder(text).reverse().toString());
        matcherLast.find();
        int lastInt = stringToInt(new StringBuilder(matcherLast.group()).reverse().toString());;
        System.out.println("For String \"" + text + "\" First is " + firstInt + " and last is " + lastInt);
        return firstInt * 10 + lastInt;
    }

    private int stringToInt(String text) {
        return switch (text) {
            case "one" -> 1;
            case "two" -> 2;
            case "three" -> 3;
            case "four" -> 4;
            case "five" -> 5;
            case "six" -> 6;
            case "seven" -> 7;
            case "eight" -> 8;
            case "nine" -> 9;
            default -> Integer.parseInt(text);
        };
    }
}
