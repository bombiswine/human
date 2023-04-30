package imit.house;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HouseTest {
    @Test(dataProvider = "houseConstructor_createsObject_thenCorrect_data")
    public static void houseConstructor_createsObject_thenCorrect_test() {

    }

    @DataProvider
    public static Object[][] houseConstructor_createsObject_thenCorrect_data() {
        return new Object[][] {
            { , },
        };
    }
}