package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> list = getWords(text);
        return list.stream()
                   .mapToInt(String::length)
                   .sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        List<String> list = getWords(text);
        return (int) list.stream()
                         .count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        List<String> list = getWords(text);
        return (int) list.stream()
                         .distinct()
                         .count();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("[^a-zA-Z]+"))
                     .collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        List<String> list = getWords(text);
        return list.stream().collect(Collectors.toSet());
    }

    /**
     * Необходимо реализовать функционал подсчета количества повторений слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должны вернуться результаты :
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text текст
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> list = getWords(text);
        return list.stream()
                .collect(Collectors.groupingBy(String::new))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().size()));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);
        return direction.equals(Direction.ASC) ?
                list.stream()
                        .sorted(Comparator.comparing(String::length))
                        .collect(Collectors.toList()) :
                list.stream()
                        .sorted(Comparator.comparing(String::length).reversed())
                        .collect(Collectors.toList());
    }
}
