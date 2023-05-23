package imit.lambdas;

import imit.human.Human;
import imit.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

import static imit.TestingData.*;
import static imit.lambdas.LambdaDemo.*;
import static imit.lambdas.LambdaRunner.applyFunction;
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
        final boolean actual = predicate.test(arg);
        assertEquals(actual, expected);
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
        predicate.test(arg);
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
        final boolean actual = biPredicate.test(argT, argU);
        assertEquals(actual, expected);
    }

    @DataProvider
    public static Object[][] biPredicate_returnsExpectedValue_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, personAlexandreMerson,  personOlgaMerson,  true },
            { ARE_NAMESAKES, studentAlexandreMerson, studentOlgaMerson, true },
            { ARE_NAMESAKES, personAlexandreMerson,  personLukeBrown,   false },
            { ARE_NAMESAKES, studentAlexandreMerson, personLukeBrown,   false },

//            {
//                ARE_YOUNGER_MAX_AGE,
//                new Human[] { personLucyEarl, personLucyEarl, personLucyEarl },
//                40,
//                true
//            },
        };
    }

    @Test(dataProvider = "biPredicate_throwsNullPointerException_thenCorrect_data")
    public static <T, U> void biPredicate_throwsNullPointerException_thenCorrect_test(
        final BiPredicate<T, U> biPredicate,
        final T argT,
        final U argU
    ) {
        biPredicate.test(argT, argU);
    }

    @DataProvider
    public static Object[][] biPredicate_throwsNullPointerException_thenCorrect_data() {
        return new Object[][] {
            { ARE_NAMESAKES, null,  personOlgaMerson },
            { ARE_NAMESAKES, personAlexandreMerson,  null },
            { ARE_NAMESAKES, null,  null },
        };
    }
}
