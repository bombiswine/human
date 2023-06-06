package imit.lambdas;

import imit.human.Human;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class StreamApiDemo extends LambdaDemo {
    public static final UnaryOperator<List<Object>> REMOVE_ALL_NULLS =
        l -> l.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

    public static final UnaryOperator<List<String>> GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS =
        strings -> strings.stream()
            .filter(Objects::nonNull)
            .filter(Predicate.not(String::isEmpty))
            .sorted(CharSequence::compare)
            .collect(Collectors.toList());

    public static final UnaryOperator<List<? extends Human>> SORT_PEOPLE_BY_GENDER_THEN_BY_AGE =
        humans -> humans.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator
                .comparing((Human h) -> h.getGender().toString())
                .thenComparingInt(Human::getAge)
            ).toList();

    private static final Predicate<Integer> IS_EVEN =  n -> n % 2 == 0;

    private static final Supplier<Integer> GET_NULL = () -> null;

    public static final Function<Set<Integer>, Integer> COUNT_POSITIVE_NUMBERS =
        s -> (int) s.stream()
            .filter(Objects::nonNull)
            .filter(number -> number > 0)
            .count();

    public static final Function<List<Object>, List<Object>> LAST_THREE_ELEMENTS =
        l -> (l.size() >= 3) ? l.stream().skip(l.size() - 3).toList() : l;

    public static final Function<List<Integer>, Integer> GET_FIRST_EVEN_NUMBER_OR_NULL =
        l -> l.stream()
            .filter(IS_EVEN)
            .findFirst()
            .orElseGet(GET_NULL);

    public static final Function<Integer[], List<Integer>> GET_SQUARES_LIST =
        ints -> Arrays.stream(ints)
            .filter(Objects::nonNull)
            .map(n -> n * n)
            .collect(Collectors.toList());

    public static final Function<Set<String>, List<String>> GET_LEX_GRAPH_BACKWARDLY_SORTED_STRING_LIST =
        set -> set.stream()
            .filter(Objects::nonNull)
            .filter(Predicate.not(String::isEmpty))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

    public static final Function<List<Integer>, Integer> GET_SQUARES_SUM =
        ints -> ints.stream()
            .reduce(0, (currentSum, n) -> currentSum + (n * n));

    public static final BiFunction<Collection<? extends Human>, Integer, Integer> COUNT_PEOPLE_OF_GIVEN_AGE =
        (humans, age) -> (int) humans.stream()
            .filter(Objects::nonNull)
            .filter(h -> h.getAge() == age)
            .count();

}
