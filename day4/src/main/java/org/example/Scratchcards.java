package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Scratchcards {

    static final Pattern pattern = Pattern.compile("Card\\s+(\\d+):\\s+(\\d+(\\s+\\d+)*)\\s+\\|\\s+(\\d+(\\s+\\d+)*)");

    public int checkPrizes(String cards){
        return Arrays.stream(cards.split("\\n")).mapToInt(x -> this.checkPrize(x).getJ()).sum();
    }

    Pair checkPrize(String card){
        Matcher matcher = pattern.matcher(card);
        if (!matcher.find())
            throw new RuntimeException();
        int cardNumber = Integer.parseInt(matcher.group(1));
        Set<Integer> winningNumbers = Arrays.stream(matcher.group(2).split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
        Set<Integer> gameNumbers = Arrays.stream(matcher.group(4).split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
        return Pair.of(cardNumber, checkPrize(winningNumbers, gameNumbers));
    }

    int checkPrize(Set<Integer> winningNumbers, Set<Integer> gameNumbers){
        List<Boolean> matches = gameNumbers.stream().map(winningNumbers::contains).filter(x -> x).toList();
        return matches.isEmpty() ? 0 : (int) Math.pow(2, matches.size() - 1);
    }

    public int bowlingPrizes(String cards){
        List<Pair> list = Arrays.stream(cards.split("\\n")).map(this::checkBowling).toList();
        Pair[] ownedCards = new Pair[list.size()];
        list.forEach(x -> {
            int index = x.getI()-1;
            int cardNumber = x.getI();
            ownedCards[index] = ownedCards[index] == null ? Pair.of(cardNumber, 1) :  ownedCards[index].increase();
            for(int i = 1; index + i < list.size() && i <= x.getJ() ; i++) {
                ownedCards[index +i] = ownedCards[index +i] == null ? Pair.of(cardNumber +i, 0) : ownedCards[index +i];
                ownedCards[index +i].increase(ownedCards[index].getJ());
            }
        });
        return Arrays.stream(ownedCards).mapToInt(Pair::getJ).sum();
    }

    Pair checkBowling(String card){
        Matcher matcher = pattern.matcher(card);
        if (!matcher.find())
            throw new RuntimeException();
        int cardNumber = Integer.parseInt(matcher.group(1));
        Set<Integer> winningNumbers = Arrays.stream(matcher.group(2).split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
        Set<Integer> gameNumbers = Arrays.stream(matcher.group(4).split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());
        return Pair.of(cardNumber, checkBowling(winningNumbers, gameNumbers));
    }

    int checkBowling(Set<Integer> winningNumbers, Set<Integer> gameNumbers){
        List<Boolean> matches = gameNumbers.stream().map(winningNumbers::contains).filter(x -> x).toList();
        return matches.isEmpty() ? 0 : matches.size();
    }

}
