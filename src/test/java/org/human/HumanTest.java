package org.human;

import org.simple_date.SimpleDate;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.zip.DataFormatException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class HumanTest {
    @Test(dataProvider = "humanConstructor_PositiveCase_Data")
    public static void humanConstructor_PositiveCase_Test(
        final FullName   fullName,
        final SimpleDate birthDate,
        final String     gender,
        final String     nationality,
        final int        height,
        final int        weight
    ) {
        Human person = new Human(
            fullName,
            birthDate,
            gender,
            nationality,
            height,
            weight
        );
        assertNotNull(person);
        assertEquals(person.getFullName(), fullName);
        assertEquals(person.getBirthDate(), birthDate);
        assertEquals(person.getGender().toString(), gender);
        assertEquals(person.getNationality(), nationality);
        assertEquals(person.getHeight(), height);
        assertEquals(person.getWeight(), weight);
    }

    @DataProvider
    public static Object[][] humanConstructor_PositiveCase_Data()
        throws DataFormatException {

        return new Object[][] {
            {
                new FullName( "Lucy", "Bella", "Earl" ),
                new SimpleDate("12.03.1998", "dd.MM.yyyy"),
                "Female",
                "English",
                175,
                65
            }
        };
    }
}
