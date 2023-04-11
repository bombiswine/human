package org.human;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class HumanTest {
    @Test(dataProvider = "humanConstructor_PositiveCase_Data")
    public static void humanConstructor_PositiveCase_Test(
        final FullName  fullName,
        final LocalDate birthDate,
        final String    gender,
        final String    nationality
    ) {
        final Human person = new Human(fullName, birthDate, gender, nationality);
        assertNotNull(person);
        assertEquals(person.getFullName(), fullName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getGender().toString(), gender);
        assertEquals(person.getNationality(), nationality);
    }

    @DataProvider
    public static Object[][] humanConstructor_PositiveCase_Data() {
        return new Object[][] {
            {
                new FullName( "Lucy", "Bella", "Earl" ),
                LocalDate.of(1998, 3, 12),
                "female",
                "English"
            }
        };
    }

    @Test(
        dataProvider = "humanConstructor_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void humanConstructor_ThrowsIllegalArgumentException_Test(
        final FullName  fullName,
        final LocalDate birthDate,
        final String    gender,
        final String    nationality
    ) {
        new Human(fullName, birthDate, gender, nationality);
    }

    @DataProvider
    public static Object[][] humanConstructor_ThrowsIllegalArgumentException_Data() {
        final FullName  fullName    = new FullName( "Lucy", "Bella", "Earl" );
        final LocalDate birthDate   = LocalDate.of(1998, 3, 12);
        final String    gender      = "Female";
        final String    nationality = "English";

        return new Object[][] {
            { null, birthDate, gender, nationality },
            { fullName, null, gender, nationality },
            { fullName, birthDate, null, nationality },
            { fullName, birthDate, "", nationality },
            { fullName, birthDate, gender, null },
            { fullName, birthDate, gender, "" },
            { fullName, birthDate, "Pansexual", nationality }
        };
    }
}
