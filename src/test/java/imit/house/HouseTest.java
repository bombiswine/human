package imit.house;


import imit.human.Human;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static imit.TestingData.*;
import static org.testng.AssertJUnit.assertEquals;

public class HouseTest {
    @Test(dataProvider = "houseConstructor_createsObject_thenCorrect_data")
    public static void houseConstructor_createsObject_thenCorrect_test(
        final Human houseHead,
        final String address,
        final String cadastralNumber,
        final List<Flat> flats
    ) {
        final House smallHouse = new House(houseHead, address, cadastralNumber, flats);
        assertEquals(houseHead, smallHouse.getHouseHead());
        assertEquals(address, smallHouse.getAddress());
        assertEquals(cadastralNumber, smallHouse.getCadastralNumber());
        assertEquals(flats, smallHouse.getFlats());
    }

    @DataProvider
    public static Object[][] houseConstructor_createsObject_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> flats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        return new Object[][] {
            { houseHead, address, cadastralNumber, flats },
        };
    }

    @Test(
        dataProvider = "houseConstructor_throwsNullPointerException_thenCorrect_data",
        expectedExceptions = NullPointerException.class
    )
    public static void houseConstructor_throwsNullPointerException_thenCorrect_test(
        final Human houseHead,
        final String address,
        final String cadastralNumber,
        final List<Flat> flats
    ) {
        new House(houseHead, address, cadastralNumber, flats);
    }

    @DataProvider
    public static Object[][] houseConstructor_throwsNullPointerException_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> flats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        return new Object[][] {
            { null, address, cadastralNumber, flats },
            { houseHead, null, cadastralNumber, flats },
            { houseHead, address, null, flats },
            { houseHead, address, cadastralNumber, null },
//            { houseHead, address, cadastralNumber, flats },
        };
    }

    @Test(
        dataProvider = "houseConstructor_throwsIllegalArgumentException_thenCorrect_data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void houseConstructor_throwsIllegalArgumentException_thenCorrect_test(
        final Human houseHead,
        final String address,
        final String cadastralNumber,
        final List<Flat> flats
    ) {
        new House(houseHead, address, cadastralNumber, flats);
    }

    @DataProvider
    public static Object[][] houseConstructor_throwsIllegalArgumentException_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> allFlats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        final List<Flat> flatsWithoutFlatOfHouseHead = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais))
        );

        return new Object[][] {
            { houseHead, "", cadastralNumber, allFlats },
            { houseHead, address, "", allFlats },
            { houseHead, "  \n \t\n", cadastralNumber, allFlats },
            { houseHead, address, "  \n \t\n", allFlats },
            { houseHead, address, cadastralNumber, List.of() },
//            { houseHead, address, cadastralNumber, flatsWithoutFlatOfHouseHead }
        };
    }

    @Test(dataProvider = "setHouseHead_changesHouseHead_thenCorrect_test")
    public static void setHouseHead_changesHouseHead_thenCorrect_test(
        House house,
        final Human newHouseHead
    ) {
        house.setHouseHead(newHouseHead);
        assertEquals(newHouseHead, house.getHouseHead());
    }

    @DataProvider
    public static Object[][] setHouseHead_changesHouseHead_thenCorrect_test() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> allFlats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        final House smallHouse = new House(houseHead, address, cadastralNumber, allFlats);

        return new Object[][] {
            { smallHouse, personPierreVeron },
            { smallHouse, personLucyBrown },
        };
    }

    @Test(
        dataProvider = "setHouseHead_throwsIllegalArgumentException_thenCorrect_data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void setHouseHead_throwsIllegalArgumentException_thenCorrect_test(
        House house,
        final Human newHouseHead
    ) {
        house.setHouseHead(newHouseHead);
    }

    @DataProvider
    public static Object[][] setHouseHead_throwsIllegalArgumentException_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> allFlats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        final House smallHouse = new House(houseHead, address, cadastralNumber, allFlats);

        return new Object[][] { { smallHouse, personArielGreen } };
    }

    @Test(
        dataProvider = "setHouseHead_throwsNullPointerException_thenCorrect_data",
        expectedExceptions = IllegalArgumentException.class
    )
    public static void setHouseHead_throwsNullPointerException_thenCorrect_test(
        final House house,
        final Human newHouseHead
    ) {
        house.setHouseHead(newHouseHead);
    }

    @DataProvider
    public static Object[][] setHouseHead_throwsNullPointerException_thenCorrect_data() {
        final Human  houseHead = personPierreVeron;
        final String address = "Clavel's Street, 14";
        final String cadastralNumber = "45";

        final List<Flat> allFlats = List.of(
            new Flat(1, 50, List.of(personAlexandreMerson)),
            new Flat(2, 60, List.of(personLucyBrown)),
            new Flat(3, 50, List.of(personAnnetBeaumarchais)),
            new Flat(4, 60, List.of(personPierreVeron))
        );

        final House smallHouse = new House(houseHead, address, cadastralNumber, allFlats);

        return new Object[][] { { smallHouse, null } };
    }
}