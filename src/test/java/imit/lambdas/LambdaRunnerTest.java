package imit.lambdas;

import imit.human.Human;
import imit.student.Student;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Function;

import static imit.TestingData.personAlexandreMerson;
import static imit.TestingData.studentAlexandreMerson;
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
}
