package imit.human;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.zip.DataFormatException;

import static imit.human.HumanService.getAdultsFrom;
import static imit.human.HumanService.getAgesArrayOf;
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
            LocalDate.of(1998, 3, 12),
            "Female",
            "English"
        );

        adultMan = new Human(
            new FullName("Alexandre", "Igorevich", "Merson"),
            LocalDate.of(2002, 6, 24),
            "Male",
            "Russian"
        );

        nonAdultGirl = new Human(
            new FullName("Annet", "", "McPhee"),
            LocalDate.of(2015, 5, 15),
            "Female",
            "English"
        );

        nonAdultBoy = new Human(
            new FullName("Julio", "", "Estades"),
            LocalDate.of(2008, 7, 6),
            "Male",
            "Spanish"
        );
    }

    @Test(dataProvider = "getAdultsFrom_ReturnsArrayOfAdults_Data")
    public static void getAdultsFrom_ReturnsArrayOfAdults_Test(
        final Human[] people,
        final Human[] expectedAdults
    ) {
        final Human[] actualAdults = getAdultsFrom(people);
        Assert.assertEquals(actualAdults, expectedAdults);
    }

    @DataProvider
    public static Object[][] getAdultsFrom_ReturnsArrayOfAdults_Data() {
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

    @Test(dataProvider = "getAgesArrayOf_ReturnsAgesArray_Data")
    public static void getAgesArrayOf_ReturnsAgesArray_Test(
        final Human[] people,
        final int[] expectedAges
    ) {
        final int[] actualAges = getAgesArrayOf(people);
        assertEquals(actualAges, expectedAges);
    }

    @DataProvider
    public static Object[][] getAgesArrayOf_ReturnsAgesArray_Data() {
        return new Object[][] {
            {
                new Human[] { adultMan, nonAdultBoy, adultWoman, nonAdultGirl },
                new int[] { 20, 14, 25, 7 }
            }, {
                new Human[] { },
                new int[] { }
            }, {
                new Human[] { adultMan, adultMan, adultMan },
                new int[] { 20, 20, 20 }
            }
        };
    }

    @Test(
        dataProvider = "getMethods_ThrowIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void getAdultsFrom_ThrowsIllegalArgumentException_Test(
        final Human[] people
    ) {
        getAdultsFrom(people);
    }

    @Test(
        dataProvider = "getMethods_ThrowIllegalArgumentException_Data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void getAgesArrayOf_ThrowsIllegalArgumentException_Test(
        final Human[] people
    ) {
        getAgesArrayOf(people);
    }

    @DataProvider
    public static Object[][] getMethods_ThrowIllegalArgumentException_Data() {
        return new Object[][] {
            { null },
            { new Human[] { null, adultMan, nonAdultBoy, adultWoman } },
            { new Human[] { adultMan, nonAdultBoy, null, adultWoman } }
        };
    }
}
