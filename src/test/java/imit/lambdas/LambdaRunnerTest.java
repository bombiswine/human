package imit.lambdas;

import imit.human.FullName;
import imit.human.Human;
import imit.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;

import static imit.TestingData.*;
import static imit.lambdas.LambdaDemo.*;
import static imit.lambdas.StreamApiDemo.*;
import static org.testng.Assert.assertEquals;

public class LambdaRunnerTest {
    @Test(dataProvider = "applyFunction_ReturnsExpectedValue_thenCorrect_data")
    public static <T, R> void applyFunction_ReturnsExpectedValue_thenCorrect_test(
        final Function<T, R> function,
        final T arg,
        final R expected
    ) {
        final R actual = LambdaRunner.applyFunction(function, arg);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] applyFunction_ReturnsExpectedValue_thenCorrect_data() {
        final Human human = personAlexandreMerson;
        final Student student = studentAlexandreMerson;

        return new Object[][] {
            { GET_STRING_LENGTH, "", 0 },
            { GET_STRING_LENGTH, "a", 1 },
            { GET_STRING_LENGTH, "string", 6 },

            { GET_STRING_FIRST_CHARACTER, "", null },
            { GET_STRING_FIRST_CHARACTER, "a", 'a' },
            { GET_STRING_FIRST_CHARACTER, "-1", '-' },
            { GET_STRING_FIRST_CHARACTER, "string", 's' },

            { COUNT_WORDS_SEPARATED_WITH_COMA, "words,separated,with,coma", 4 },
            { COUNT_WORDS_SEPARATED_WITH_COMA, "", 0 },
            { COUNT_WORDS_SEPARATED_WITH_COMA, ",", 0 },
            { COUNT_WORDS_SEPARATED_WITH_COMA, ",,,", 0 },
            { COUNT_WORDS_SEPARATED_WITH_COMA, ", , ,", 2 },

            { GET_AGE, human, human.getAge() },
            { GET_AGE, student, student.getAge() },

            {
                GET_FULLNAME_STRING,
                new Human(
                    new FullName("Alexandre", "Igorevich", "Merson"),
                    LocalDate.of(2002, 6, 24),
                    "Male",
                    "Russian"
                ),
                "Merson Alexandre Igorevich"
            },
            {
                GET_FULLNAME_STRING,
                new Student(
                    new FullName("Alexandre", "Igorevich", "Merson"),
                    LocalDate.of(2002, 6, 24),
                    "Male",
                    "Russian",
                    "OmSU",
                    "MathFac",
                    "Mathematics"
                ),
                "Merson Alexandre Igorevich"

            },
            {
                MAKE_ONE_YEAR_OLDER,
                new Human(
                    new FullName("Alexandre", "Igorevich", "Merson"),
                    LocalDate.of(2002, 6, 24),
                    "Male",
                    "Russian"
                ),
                new Human(
                    new FullName("Alexandre", "Igorevich", "Merson"),
                    LocalDate.of(2001, 6, 24),
                    "Male",
                    "Russian"
                )
            },

            { COUNT_POSITIVE_NUMBERS, Set.of(0, -1, 5), 1 },
            { COUNT_POSITIVE_NUMBERS, Set.of(0, -1, -5), 0 },
            { COUNT_POSITIVE_NUMBERS, Set.of(0, 1, 5), 2 },
            { COUNT_POSITIVE_NUMBERS, Set.of(4, 1, 5), 3 },
            { COUNT_POSITIVE_NUMBERS, Set.of(0), 0 },
            { COUNT_POSITIVE_NUMBERS, Set.of(), 0 },
            { COUNT_POSITIVE_NUMBERS, Set.of(-5), 0 },
            {
                COUNT_POSITIVE_NUMBERS,
                new HashSet<>((Arrays.asList(null, 5, null, -3, 1))),
                2
            },

            {
                LAST_THREE_ELEMENTS,
                List.of("Hola", "Hi", "Hei", "Ciao", "Coucou", "Czesc"),
                List.of("Ciao", "Coucou", "Czesc")
            },
            {
                LAST_THREE_ELEMENTS,
                List.of(1, 2, 3, 4, 5, 6, 7, 8, 9),
                List.of(7, 8, 9)
            },
            {
                LAST_THREE_ELEMENTS,
                List.of(1, 2, 3),
                List.of(1, 2, 3)
            },
            {
                LAST_THREE_ELEMENTS,
                List.of(1, 2),
                List.of(1, 2)
            },

            { GET_FIRST_EVEN_NUMBER_OR_NULL, List.of(2, 4, 6), 2 },
            { GET_FIRST_EVEN_NUMBER_OR_NULL, List.of(1, 4, 6), 4 },
            { GET_FIRST_EVEN_NUMBER_OR_NULL, List.of(1, 3, 6), 6 },
            { GET_FIRST_EVEN_NUMBER_OR_NULL, List.of(1, 3, 5), null },
            { GET_FIRST_EVEN_NUMBER_OR_NULL, List.of(), null },

            {
                GET_SQUARES_LIST,
                new Integer[] { 2, -1, 10, 0, -5 },
                List.of(4, 1, 100, 0, 25)
            },
            {
                GET_SQUARES_LIST,
                new Integer[ 0 ],
                List.of()
            },
            {
                GET_SQUARES_LIST,
                new Integer[] { -1, null, 0, null, 2 },
                List.of(1, 0, 4)
            },
            {
                GET_SQUARES_LIST,
                new Integer[] { null, null, null },
                List.of()
            },

            {
                GET_LEX_GRAPH_BACKWARDLY_SORTED_STRING_LIST,
                Set.of("Astring", "Ystring", "Bstring", "Xstring"),
                List.of("Ystring", "Xstring", "Bstring", "Astring")
            },
            {
                GET_LEX_GRAPH_BACKWARDLY_SORTED_STRING_LIST,
                Set.of("Astring"),
                List.of("Astring")
            },
            {
                GET_LEX_GRAPH_BACKWARDLY_SORTED_STRING_LIST,
                Set.of(),
                List.of()
            },

            { GET_SQUARES_SUM, List.of(-1, 1, -1, 1), 4 },
            { GET_SQUARES_SUM, List.of(-1), 1 },
            { GET_SQUARES_SUM, List.of(0), 0 },
            { GET_SQUARES_SUM, List.of(), 0 },
        };
    }

    @Test(
        dataProvider = "applyFunction_throwsNullPointerException_thenCorrect_test",
        expectedExceptions = NullPointerException.class
    )
    public static <T, R> void applyFunction_throwsNullPointerException_thenCorrect_test(
        final Function<T, R> function,
        final T arg
    ) {
        LambdaRunner.applyFunction(function, arg);
    }

    @DataProvider
    public static Object[][] applyFunction_throwsNullPointerException_thenCorrect_test() {
        return new Object[][] {
            { GET_STRING_LENGTH, null },
            { GET_STRING_FIRST_CHARACTER, null },
            { COUNT_WORDS_SEPARATED_WITH_COMA, null },
            { GET_AGE, null },
            { GET_FULLNAME_STRING, null },
            { MAKE_ONE_YEAR_OLDER, null },
            { COUNT_POSITIVE_NUMBERS, null },
            { LAST_THREE_ELEMENTS, null },
            { GET_FIRST_EVEN_NUMBER_OR_NULL, null },
            { GET_SQUARES_LIST, null },
            { GET_LEX_GRAPH_BACKWARDLY_SORTED_STRING_LIST, null },
            { GET_SQUARES_SUM, null },
        };
    }

    //////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "testPredicate_returnsExpectedValue_thenCorrect_data")
    public static <T> void testPredicate_returnsExpectedValue_thenCorrect_test(
        final Predicate<T> predicate,
        final T arg,
        final boolean expected
    ) {
        assertEquals(LambdaRunner.testPredicate(predicate, arg), expected);
    }

    @DataProvider
    public static Object[][] testPredicate_returnsExpectedValue_thenCorrect_data() {
        return new Object[][] {
            { NO_WHITE_SPACE_IN_STRING, "NoWhiteSpacesHere", true },
            { NO_WHITE_SPACE_IN_STRING, "Here's some spaces", false },
            { NO_WHITE_SPACE_IN_STRING, "", true },
            { NO_WHITE_SPACE_IN_STRING, " ", false },
        };
    }

    @Test(
        dataProvider = "testPredicate_throwsNullPointerException_thenCorrect_test",
        expectedExceptions = NullPointerException.class
    )
    public static <T> void testPredicate_throwsNullPointerException_thenCorrect_test(
        final Predicate<T> predicate,
        final T arg
    ) {
        LambdaRunner.testPredicate(predicate, arg);
    }

    @DataProvider
    public static Object[][] testPredicate_throwsNullPointerException_thenCorrect_test() {
        return new Object[][] {
            { NO_WHITE_SPACE_IN_STRING, null }
        };
    }

    //////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "biPredicate_returnsExpectedValue_thenCorrect_data")
    public static <T, U> void biPredicate_returnsExpectedValue_thenCorrect_test(
        final BiPredicate<T, U> biPredicate,
        final T argT,
        final U argU,
        final boolean expected
    ) {
        assertEquals(LambdaRunner.testBiPredicate(biPredicate, argT, argU), expected);
    }

    @DataProvider
    public static Object[][] biPredicate_returnsExpectedValue_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, personAlexandreMerson, personOlgaMerson, true },
            { ARE_NAMESAKES, studentAlexandreMerson, studentOlgaMerson, true },
            { ARE_NAMESAKES, personAlexandreMerson, personLukeBrown, false },
            { ARE_NAMESAKES, studentAlexandreMerson, personLukeBrown, false },

            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson
                },
                0,
                false
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson
                },
                121,
                true
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson
                },
                -121,
                false
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson
                },
                2,
                false
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson
                },
                40,
                true
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[] {
                    personLucyEarl,
                    personAlexandreMerson,
                    personOlgaMerson
                },
                40,
                false
            },
            {
                ARE_YOUNGER_MAX_AGE,
                new Human[ 0 ],
                40,
                true
            },
        };
    }

    @Test(
        dataProvider = "biPredicate_throwsNullPointerException_thenCorrect_data",
        expectedExceptions = NullPointerException.class
    )
    public static <T, U> void biPredicate_throwsNullPointerException_thenCorrect_test(
        final BiPredicate<T, U> biPredicate,
        final T argT,
        final U argU
    ) {
        LambdaRunner.testBiPredicate(biPredicate, argT, argU);
    }

    @DataProvider
    public static Object[][] biPredicate_throwsNullPointerException_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, null, personOlgaMerson },
            { ARE_NAMESAKES, personAlexandreMerson, null },
            { ARE_NAMESAKES, null, null },
        };
    }

    /////////////////////////////////////////////////////////////////////////////////////
    @Test(dataProvider = "applyUnaryOperator_returnsExpectedResult_thenCorrect_data")
    public static <T> void applyUnaryOperator_returnsExpectedResult_thenCorrect_test(
        final UnaryOperator<T> unaryOperator,
        final T arg,
        final T expected
    ) {
        final T actual = LambdaRunner.applyUnaryOperator(unaryOperator, arg);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] applyUnaryOperator_returnsExpectedResult_thenCorrect_data() {
        return new Object[][] {
            {
                REMOVE_ALL_NULLS,
                Arrays.asList(null, "Im' not null", "null", null),
                List.of("Im' not null", "null")
            },
            {
                REMOVE_ALL_NULLS,
                Arrays.asList(null, null, null),
                List.of()
            },
            {
                REMOVE_ALL_NULLS,
                List.of(12, 25, -4, 2),
                List.of(12, 25, -4, 2),
            },
            {
                REMOVE_ALL_NULLS,
                List.of(),
                List.of(),
            },
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////
            {
                GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS,
                List.of("bae", "pop", "ojojo", "la la"),
                List.of("bae", "la la", "ojojo", "pop")
            },
            {
                GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS,
                List.of("bae", "Bae", "b a e"),
                List.of("Bae", "b a e", "bae")
            },
            {
                GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS,
                List.of("bae", "pop", "ojojo", "la la"),
                List.of("bae", "la la", "ojojo", "pop")
            },
            {
                GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS,
                List.of("a string"),
                List.of("a string")
            }, {
            GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS,
            List.of(),
            List.of()
        },
            {
                SORT_PEOPLE_BY_GENDER_THEN_BY_AGE,
                List.of(
                    new Human(
                        new FullName("Alexandre", "", "Merson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "Russian"
                    ),
                    new Human(
                        new FullName("Lucy", "", "Earl"),
                        LocalDate.of(2004, 5, 24),
                        "Female",
                        "English"
                    ),
                    new Human(
                        new FullName("Ioan", "", "Miczewski"),
                        LocalDate.of(2000, 1, 10),
                        "Male",
                        "Polish"
                    ),
                    new Human(
                        new FullName("Mary", "", "Green"),
                        LocalDate.of(2000, 7, 14),
                        "Female",
                        "English"
                    )
                ),
                List.of(
                    new Human(
                        new FullName("Lucy", "", "Earl"),
                        LocalDate.of(2004, 5, 24),
                        "Female",
                        "English"
                    ),
                    new Human(
                        new FullName("Mary", "", "Green"),
                        LocalDate.of(2000, 7, 14),
                        "Female",
                        "English"
                    ),
                    new Human(
                        new FullName("Alexandre", "", "Merson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "Russian"
                    ),
                    new Human(
                        new FullName("Ioan", "", "Miczewski"),
                        LocalDate.of(2000, 1, 10),
                        "Male",
                        "Polish"
                    )
                ),
            },
        };
    }

    @Test(
        dataProvider = "applyUnaryOperator_throwsNullPointerException_thenCorrect_test",
        expectedExceptions = NullPointerException.class
    )
    public static <T> void applyUnaryOperator_throwsNullPointerException_thenCorrect_test(
        final UnaryOperator<T> unaryOperator,
        final T arg
    ) {
        LambdaRunner.applyFunction(unaryOperator, arg);
    }

    @DataProvider
    public static Object[][] applyUnaryOperator_throwsNullPointerException_thenCorrect_test() {
        return new Object[][] {
            { REMOVE_ALL_NULLS, null },
            { GET_LEX_GRAPH_SORTED_NON_EMPTY_STRINGS, null },
            { SORT_PEOPLE_BY_GENDER_THEN_BY_AGE, null },
        };
    }

    @Test(dataProvider = "applyBiFunction_returnsExpectedValue_thenCorrect_data")
    public static <T, U, R> void applyBiFunction_returnsExpectedValue_thenCorrect_test(
        final BiFunction<T, U, R> biFunction,
        final T argT,
        final U argU,
        final R expected
    ) {
        final R actual = LambdaRunner.applyBiFunction(biFunction, argT, argU);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] applyBiFunction_returnsExpectedValue_thenCorrect_data() {
        return new Object[][] {
            {
                COUNT_PEOPLE_OF_GIVEN_AGE,
                List.of(
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "Russian"
                    ),
                    new Human(
                        new FullName("Camile", "", "Peterson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "French"
                    ),
                    new Human(
                        new FullName("George", "", "Brown"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "English"
                    ),
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "Russian"
                    )
                ),
                Period.between(LocalDate.of(2002, 6, 24), LocalDate.now()).getYears(),
                4
            },

            {
                COUNT_PEOPLE_OF_GIVEN_AGE,
                List.of(
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2002, 6, 24),
                        "Male",
                        "Russian"
                    ),
                    new Human(
                        new FullName("Camile", "", "Peterson"),
                        LocalDate.of(2003, 6, 24),
                        "Male",
                        "French"
                    ),
                    new Human(
                        new FullName("George", "", "Brown"),
                        LocalDate.of(2012, 6, 24),
                        "Male",
                        "English"
                    ),
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2000, 6, 24),
                        "Male",
                        "Russian"
                    )
                ),
                Period.between(LocalDate.of(2002, 6, 24), LocalDate.now()).getYears(),
                1
            },
            {
                COUNT_PEOPLE_OF_GIVEN_AGE,
                List.of(
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2001, 6, 24),
                        "Male",
                        "Russian"
                    ),
                    new Human(
                        new FullName("Camile", "", "Peterson"),
                        LocalDate.of(2003, 6, 24),
                        "Male",
                        "French"
                    ),
                    new Human(
                        new FullName("George", "", "Brown"),
                        LocalDate.of(2012, 6, 24),
                        "Male",
                        "English"
                    ),
                    new Human(
                        new FullName("Alexandre", "Igorevich", "Merson"),
                        LocalDate.of(2000, 6, 24),
                        "Male",
                        "Russian"
                    )
                ),
                Period.between(LocalDate.of(2002, 6, 24), LocalDate.now()).getYears(),
                0
            },
            {
                COUNT_PEOPLE_OF_GIVEN_AGE,
                List.of(),
                Period.between(LocalDate.of(2002, 6, 24), LocalDate.now()).getYears(),
                0
            },
        };
    }
}
