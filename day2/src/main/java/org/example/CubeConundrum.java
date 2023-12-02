package org.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubeConundrum {
    private final int red = 12;
    private final int green = 13;
    private final int blue = 14;

    public int sumOfIds(String games) {
        return Arrays.stream(games.split("\\n")).mapToInt(this::game).sum();
    }

    int game(String game) {
        Matcher matcher = Pattern.compile("Game (\\d+): (.*)").matcher(game);
        matcher.find();
        int gameId = Integer.parseInt(matcher.group(1));
        String[] draws = matcher.group(2).split(";");
        for (String draw : draws) {
            if (!isPossible(draw))
                return 0;
        }
        return gameId;
    }

    boolean isPossible(String draw) {
        String[] cubes = draw.split(",");
        for (String cube : cubes) {
            Matcher matcher = Pattern.compile("\\s*(\\d+) (red|green|blue)").matcher(cube);
            matcher.find();
            int count = Integer.parseInt(matcher.group(1));
            String color = matcher.group(2);
            switch (color) {
                case "blue":
                    if (count > blue) return false;
                case "red":
                    if (count > red) return false;
                case "green":
                    if (count > green) return false;
            }
        }
        return true;
    }

    public int powerOfMinimums(String games) {
        return Arrays.stream(games.split("\\n")).mapToInt(this::powerOfMinimum).sum();
    }

    int powerOfMinimum(String game) {
        int[] rgb = {0,0,0};
        Matcher matcher = Pattern.compile("Game (\\d+): (.*)").matcher(game);
        matcher.find();
        int gameId = Integer.parseInt(matcher.group(1));
        String[] draws = matcher.group(2).split(";");
        for (String draw : draws) {
            power(draw, rgb);
        }
        return rgb[0] * rgb[1] * rgb[2];
    }

    int[] power(String draw, int[] rgb) {
        String[] cubes = draw.split(",");
        for (String cube : cubes) {
            Matcher matcher = Pattern.compile("\\s*(\\d+) (red|green|blue)").matcher(cube);
            matcher.find();
            int count = Integer.parseInt(matcher.group(1));
            String color = matcher.group(2);
            switch (color) {
                case "red":
                    if (count > rgb[0]) rgb[0] = count;
                    break;
                case "green":
                    if (count > rgb[1]) rgb[1] = count;
                    break;
                case "blue":
                    if (count > rgb[2]) rgb[2] = count;
                    break;
            }
        }
        return rgb;
    }
}
