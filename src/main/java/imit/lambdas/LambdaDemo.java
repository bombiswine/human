package imit.lambdas;

import imit.human.Human;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaDemo {
    final static Function<String, Integer> GET_STRING_LENGTH = String::length;
    final static Function<String, Character> GET_STRING_FIRST_CHARACTER = s -> s.isEmpty() ? null : s.charAt(0);
    final static Function<String, Integer> COUNT_WORDS_SEPARATED_WITH_COMA =
        s -> (int) Arrays.stream(s.split(","))
            .filter(ss -> !ss.isEmpty())
            .count();
    final static Function<? extends Human, Integer> GET_AGE = Human::getAge;
    final static Function<? extends Human, String> GET_FULLNAME_STRING =
        h -> new StringBuilder()
            .append(h.getSurname())
            .append(h.getMiddleName())
            .append(h.getName())
            .toString();
    final static Function<Human, Human> MAKE_ONE_YEAR_OLDER =
        h -> new Human(
            h.getFullName(),
            h.getBirthDate().plusYears(1),
            h.getGender().toString(),
            h.getNationality()
        );

    final static Predicate<String> NO_WHITE_SPACE_IN_STRING = s -> !s.contains(" ");


    final static BiPredicate<? extends Human, ? extends Human> NAMESAKES =
        (h1, h2) -> Objects.equals(h1.getSurname(), h2.getSurname());
    final static BiPredicate<Human, Integer> YOUNGER_THAN_MAX_AGE = (h, i) -> h.getAge() > i;

    final static BiFunction<Human[], Integer, Boolean> ARE_YOUNGER_MAX_AGE =
        (Human[] humans, Integer age) -> Arrays.stream(humans).allMatch(h -> YOUNGER_THAN_MAX_AGE.test(h, age));
}
