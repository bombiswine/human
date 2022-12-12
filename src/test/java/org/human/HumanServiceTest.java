package org.human;

import org.human.utilities.FullName;
import org.simple_date.SimpleDate;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.zip.DataFormatException;

import static org.human.HumanService.getAdultsFrom;
import static org.testng.Assert.assertEquals;

public class HumanServiceTest {
    private static Human adultWoman;
    private static Human adultMan;
    private static Human nonAdultGirl;
    private static Human nonAdultBoy;

    @BeforeTest
    public static void setUp() throws DataFormatException {
        adultWoman = new Human(
            new FullName("Lucy", "Bella", "Earl"),
            new SimpleDate("12.03.1998", "dd.MM.yyyy"),
            "Female",
            "English",
            175,
            55
        );

        adultMan = new Human(
            new FullName("Alexandre", "Igorevich", "Merson"),
            new SimpleDate("24.06.2002", "dd.MM.yyyy"),
            "Male",
            "Russian",
            178,
            82
        );

        nonAdultGirl = new Human(
            new FullName("Annet", "", "McPhee"),
            new SimpleDate("15.05.2015", "dd.MM.yyyy"),
            "Female",
            "English",
            135,
            40
        );

        nonAdultBoy = new Human(
            new FullName("Julio", "", "Estades"),
            new SimpleDate("06.07.2008", "dd.MM.yyyy"),
            "Male",
            "Spanish",
            178,
            68
        );
    }

    @Test(dataProvider = "getAdultsFrom_ReturnsArrayOfAdults_Data")
    public static void getAdultsFrom_ReturnsArrayOfAdults_Test(
        final Human[] people,
        final Human[] expectedAdults
    ) {
        final Human[] actualAdults = getAdultsFrom(people);
        assertEquals(actualAdults, expectedAdults);
    }

    @DataProvider
    public static Object[][] getAdultsFrom_ReturnsArrayOfAdults_Data()
        throws DataFormatException {
        return new Object[][] {
            {
                new Human[] { adultMan, nonAdultBoy, adultWoman, nonAdultGirl },
                new Human[] { adultMan, adultWoman }
            }, {
                new Human[] { adultMan, adultWoman },
                new Human[] { adultMan, adultWoman }
            }, {
                new Human[] { nonAdultBoy, nonAdultGirl },
                new Human[] { }
            }
        };
    }

    @Test(
        dataProvider = "getAdultsFrom_ThrowsIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void getAdultsFrom_ThrowsIllegalArgumentException_Test(
        final Human[] people
    ) {
        getAdultsFrom(people);
    }

    @DataProvider
    public static Object[][] getAdultsFrom_ThrowsIllegalArgumentException_Data() {
        return new Object[][] {
            { null },
            { new Human[] { null, adultMan, nonAdultBoy, adultWoman } },
            { new Human[] { adultMan, nonAdultBoy, null, adultWoman } }
        };
    }
}
