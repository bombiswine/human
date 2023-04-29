package imit.human;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class FullNameTest {
    @Test(dataProvider = "fullNameConstructor_PositiveCase_Data")
    public static void fullNameConstructor_PositiveCase_Test(
        final String firstName,
        final String middleName,
        final String surname
    ) {
        FullName fullName = new FullName(
            firstName, middleName, surname
        );
        assertNotNull(fullName);
        assertEquals(fullName.getFirstName(), firstName);
        assertEquals(fullName.getMiddleName(), middleName);
        assertEquals(fullName.getSurname(), surname);
    }

    @DataProvider
    public static Object[][] fullNameConstructor_PositiveCase_Data() {
        return new Object[][] {
            { "Lucy", "Bella", "Earl" },
            { "Teofil", "", "Ghoties" },
            { "Alexander", "Igorevich", "Merson" },
        };
    }

    @Test(
        dataProvider = "fullNameConstructor_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void fullNameConstructor_ThrowsIllegalArgumentException_Test(
        final String firstName,
        final String middleName,
        final String surname
    ) {
        new FullName(firstName, middleName, surname);
    }

    @DataProvider
    public static Object[][] fullNameConstructor_ThrowsIllegalArgumentException_Data() {
        return new Object[][] {
            { null, "Bella", "Earl" },
            { "Lucy", null, "Earl" },
            { "Lucy", "Bella", null },
            { null, null, null },
            { "", "Bella", "Earl" },
            { "Lucy", "Bella", "" },
            { "Lucy", "", "" },
            { "", "", "Earl" },
            { "", "", "" }
        };
    }
}
