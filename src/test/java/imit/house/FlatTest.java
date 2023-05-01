package imit.house;

import imit.human.Human;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static imit.TestingData.*;
import static org.testng.Assert.assertEquals;

public class FlatTest {
    @Test(dataProvider = "flatConstructor_createsObject_thenCorrect_data")
    public static void flatConstructor_createsObject_thenCorrect_test(
        final int number,
        final int area,
        final List<Human> owners
    ) {
        final Flat flat = new Flat(number, area, owners);
        assertEquals(flat.getNumber(), number);
        assertEquals(flat.getArea(), area);
        assertEquals(flat.getOwners(), owners);
    }

    @DataProvider
    public static Object[][] flatConstructor_createsObject_thenCorrect_data() {
        return new Object[][] {
            { 41, 50, List.of(personAlexandreMerson, personPierreVeron) },
            { 41, 50, List.of(studentAlexandreMerson, personPierreVeron) },
            { 41, 50, List.of(personPierreVeron) },
        };
    }

    @Test(
        dataProvider = "flatConstructor_throwsIllegalArgumentException_thenCorrect_test",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void flatConstructor_throwsIllegalArgumentException_thenCorrect_test(
        final int number,
        final int area,
        final List<Human> owners
    ) {
        new Flat(number, area, owners);
    }

    @DataProvider
    public static Object[][] flatConstructor_throwsIllegalArgumentException_thenCorrect_test() {
        return new Object[][] {
            { -41,  50, List.of(studentAlexandreMerson, personPierreVeron) },
            {  41, -50, List.of(studentAlexandreMerson, personPierreVeron) },
            {  41,  50, List.of() },
        };
    }

    @Test(
        dataProvider = "flatConstructor_throwsNullPointerException_thenCorrect_test",
        expectedExceptions = NullPointerException.class
    )
    public static void flatConstructor_throwsNullPointerException_thenCorrect_test(
        final int number,
        final int area,
        final List<Human> owners
    ) {
        new Flat(number, area, owners);
    }

    @DataProvider
    public static Object[][] flatConstructor_throwsNullPointerException_thenCorrect_test() {
        return new Object[][] { { 41,  50, null } };
    }

    // help required
    @Test(dataProvider = "changeOwners_returnsFlatObject_thenCorrect_data")
    public static void changeOwners_returnsFlatObject_thenCorrect_test(
        final Flat flat,
        final List<Human> newOwners
    ) {
        Flat.changeOwners(flat, newOwners);
//        assertEquals(newOwners, flat.getOwners());
    }

    @DataProvider
    public static Object[][] changeOwners_returnsFlatObject_thenCorrect_data() {
        final Flat flat = new Flat(41,  50, List.of(personAlexandreMerson, personPierreVeron));
        return new Object[][] {
            { flat, List.of(personAlexandreMerson) },
        };
    }
}
