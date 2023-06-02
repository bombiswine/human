package imit.lambdas;

import imit.human.FullName;
import imit.human.Human;
import imit.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static imit.TestingData.*;
import static imit.lambdas.LambdaDemo.*;
import static imit.lambdas.LambdaRunner.*;
import static imit.lambdas.StreamApiDemo.*;
import static org.testng.Assert.assertEquals;

public class LambdaRunnerTest {
    @Test(dataProvider = "applyFunction_ReturnsExpectedValue_thenCorrect_data")
    public static <T, R> void applyFunction_ReturnsExpectedValue_thenCorrect_test(
        final Function<T, R> function,
        final T arg,
        final R expected
    ) {
        final R actual = applyFunction(function, arg);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] applyFunction_ReturnsExpectedValue_thenCorrect_data() {
        final Human   human = personAlexandreMerson;
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

            { GET_AGE, human , human.getAge() },
            { GET_AGE, student , student.getAge() },

            {
                GET_FULLNAME_STRING,
                human,
                human.getSurname() + human.getMiddleName() + human.getName()
            },
            {
                GET_FULLNAME_STRING,
                student,
                student.getSurname() + student.getMiddleName() + student.getName()
            },

            {
                MAKE_ONE_YEAR_OLDER,
                human,
                new Human(
                    human.getFullName(),
                    human.getBirthDate().plusYears(1),
                    human.getGender().toString(),
                    human.getNationality()
                )
            },
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
       applyFunction(function, arg);
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
        };
    }

    //////////////////////////////////////////////////////////////////////////////////////
    @Test(dataProvider = "testPredicate_returnsExpectedValue_thenCorrect_data")
    public static <T> void testPredicate_returnsExpectedValue_thenCorrect_test(
        final Predicate<T> predicate,
        final T arg,
        final boolean expected
    ) {
        assertEquals(testPredicate(predicate, arg), expected);
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
        testPredicate(predicate, arg);
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
        assertEquals(testBiPredicate(biPredicate, argT, argU), expected);
    }

    @DataProvider
    public static Object[][] biPredicate_returnsExpectedValue_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, personAlexandreMerson,  personOlgaMerson,  true },
            { ARE_NAMESAKES, studentAlexandreMerson, studentOlgaMerson, true },
            { ARE_NAMESAKES, personAlexandreMerson,  personLukeBrown,   false },
            { ARE_NAMESAKES, studentAlexandreMerson, personLukeBrown,   false },

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
        testBiPredicate(biPredicate, argT, argU);
    }

    @DataProvider
    public static Object[][] biPredicate_throwsNullPointerException_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, null,  personOlgaMerson },
            { ARE_NAMESAKES, personAlexandreMerson,  null },
            { ARE_NAMESAKES, null,  null },
        };
    }
    /////////////////////////////////////////////////////////////////////////////////////
    @Test(dataProvider = "applyUnaryOperator_returnsExpectedResult_thenCorrect_data")
    public static <T> void applyUnaryOperator_returnsExpectedResult_thenCorrect_test(
        final UnaryOperator<T> unaryOperator,
        final T arg,
        final T expected
    ) {
        final T actual = applyUnaryOperator(unaryOperator, arg);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] applyUnaryOperator_returnsExpectedResult_thenCorrect_data() {
        return new Object[][] {
            {
                REMOVE_ALL_NULLS,
                Arrays.asList(null, "Im' not null", "null", null ),
                List.of("Im' not null", "null")
            },
            {
                REMOVE_ALL_NULLS,
                Arrays.asList(null, null, null ),
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
                List.of("bae", "Bae" , "b a e"),
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
            },  {
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
                        new FullName("Ioan", "", "Miczewski"),
                        LocalDate.of(2000, 1, 10),
                        "Male",
                        "Polish"
                    ),
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
        applyFunction(unaryOperator, arg);
    }

    @DataProvider
    public static Object[][] applyUnaryOperator_throwsNullPointerException_thenCorrect_test() {
        return new Object[][] {
            { , },
        };
    }
}
