package imit.flat;

import imit.human.Human;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

import static imit.TestingData.*;

public class FlatTest {
    static final List<? extends Human> flatOwners = List.of(
        studentAlexandreMerson,
        personPierreVeron
    );

    @BeforeClass
    public static void setUp() {
    }

    @AfterClass
    public static void tearDown() {

    }
}
