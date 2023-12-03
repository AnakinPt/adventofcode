package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GearRatios {

    Pattern pattern = Pattern.compile("(\\d+)");
    Pattern symbols = Pattern.compile("([@£§#$%&/=*\\-+])");
    Pattern star = Pattern.compile("(\\*)");
    public int sumOfPartNumbers(String machine) {
        String[] map = machine.split("\\n");
        int lineNumber = 0;
        int sumOfPartNumbers = 0;
        for (String line : map) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String number = matcher.group();
                int start = matcher.start() - (matcher.start() > 0 ? 1 : 0);
                int end = matcher.end()+(matcher.end() < line.length() ? 1 : 0);
                if (isPartNumber(lineNumber > 0 ? map[lineNumber - 1].substring(start, end) : null,
                        line.substring(start, end),
                        lineNumber < map.length - 1 ? map[lineNumber + 1].substring(start, end) : null)) {
                    sumOfPartNumbers += Integer.parseInt(number);
                    System.out.println("Found part "+number);
                }
            }
            lineNumber++;
        }
        return sumOfPartNumbers;
    }

    boolean isPartNumber(String previous, String line, String next) {
        if (previous != null && checkSymbol(previous)) {
            return true;
        }
        if (next != null && checkSymbol(next)) {
            return true;
        }
        return line != null && checkSymbol(line);
    }

    boolean checkSymbol(String line) {
        Matcher matcher = symbols.matcher(line);
        return matcher.find();
    }

    int gearRatios(String machine){
        String[] map = machine.split("\\n");
        int lineNumber = 0;
        Map<Pair, List<Integer>> gears = new HashMap<>();
        for (String line : map) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String number = matcher.group();
                int start = matcher.start() - (matcher.start() > 0 ? 1 : 0);
                int end = matcher.end()+(matcher.end() < line.length() ? 1 : 0);

                Set<Pair> potentialGear = isPotentialGear(lineNumber > 0 ? map[lineNumber - 1].substring(start, end) : null,
                        line.substring(start, end),
                        lineNumber < map.length - 1 ? map[lineNumber + 1].substring(start, end) : null,
                        lineNumber, start);
                if (!potentialGear.isEmpty()) {
                    for(Pair pair : potentialGear){
                        List<Integer> integers = gears.get(pair);
                        if (integers == null)
                            integers = new ArrayList<>();
                        integers.add(Integer.parseInt(number));
                        gears.put(pair, integers);
                    }
                    System.out.println("Found part " + number);
                }
            }
            lineNumber++;
        }
        int gearvalue = 0;
        for(List<Integer> gear: gears.values()){
            if (!gear.isEmpty() && gear.size() > 1)
            {
                gearvalue+=gear.stream().reduce(1, (a, b) -> a * b);
            }
        }
        return gearvalue;
    }

    Set<Pair> isPotentialGear(String previous, String line, String next, int lineNumber, int startColumn) {
        Set<Pair> positions = new HashSet<>();
        if (previous != null ) {
            positions.add(checkPotentialGear(previous, lineNumber-1, startColumn));
        }
        if (next != null) {
            positions.add(checkPotentialGear(next, lineNumber + 1, startColumn));
        }
        positions.add(checkPotentialGear(line, lineNumber, startColumn));
        positions.remove(null);
        return positions;
    }

    Pair checkPotentialGear(String line, int lineNumber, int startColumn) {
        Matcher matcher = star.matcher(line);
        return matcher.find()?Pair.of(lineNumber, startColumn + matcher.start()):null;
    }


}
