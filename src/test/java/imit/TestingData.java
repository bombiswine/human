package imit;

import imit.human.FullName;
import imit.human.Human;
import imit.student.Student;

import java.time.LocalDate;

public class TestingData {
    public final static Human personAlexandreMerson = new Human(
        new FullName("Alexandre", "Igorevich", "Merson"),
        LocalDate.of(2002, 6, 24),
        "Male",
        "Russian"
    );
    public final static Student studentAlexandreMerson = new Student(
        personAlexandreMerson,
        "Omsu",
        "IMIT",
        "Mathematics"
    );

    public final static Human personPierreVeron = new Human(
        new FullName("Pierre", "", "Veron"),
        LocalDate.of(2002, 6, 24),
        "Male",
        "French"
    );
    public final static Student studentPierreVeron = new Student(
        personPierreVeron,
        "Sarbonne",
        "Phylosophy",
        "Phylosophy"
    );

    public final static Human personJulieVirth = new Human(
        new FullName("Julie", "", "Virth"),
        LocalDate.of(2002, 6, 24),
        "Female",
        "Swiss"
    );
    public final static Student studentJulieVirth = new Student(
        personJulieVirth,
        "Omsu",
        "MCSF",
        "Economics"
    );


    public final static Human personCyrillVirth = new Human(
        new FullName("Cyrill", "", "Virth"),
        LocalDate.of(1976, 12, 6),
        "Male",
        "Russian"
    );
    public final static Student studentCyrillVirth = new Student(
        personCyrillVirth,
        "Omsu",
        "MCSF",
        "Biology"
    );

    public final static Human personOlgaMerson = new Human(
        new FullName("Olga", "Vladimirovna", "Merson"),
        LocalDate.of(1976, 12, 6),
        "Female",
        "Russian"
    );
    public final static Student studentOlgaMerson = new Student(
        personOlgaMerson,
        "OmSMU",
        "Healthcare",
        "Psychiatry"
    );


    public final static Human personLucyEarl = new Human(
        new FullName("Lucy", "", "Earl"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyEarl = new Student(
        personLucyEarl,
        "Oxford",
        "Linguistics",
        "Spanish"
    );

    public final static Human personLukeBrown = new Human(
        new FullName("Luke", "", "Brown"),
        LocalDate.of(2000, 5, 12),
        "Male",
        "English"
    );
    public final static Student studentLukeBrown = new Student(
        personLukeBrown,
        "Harward",
        "Sciences",
        "Organic Chemistry"
    );

    public final static Human personAlexBabington = new Human(
        new FullName("Alex", "", "Babington"),
        LocalDate.of(2000, 5, 12),
        "Male",
        "English"
    );
    public final static Student studentAlexBabington = new Student(
        personAlexBabington,
        "Harward",
        "Sciences",
        "Organic Chemistry"
    );

    public final static Human personLucyBrown = new Human(
        new FullName("Lucy", "", "Brown"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyBrown = new Student(
        personLucyBrown,
        "Cambridge",
        "Linguistics",
        "English Literature"
    );

    public final static Human personLucyVirth = new Human(
        new FullName("Lucy", "", "Virth"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyVirth = new Student(
        personLucyVirth,
        "Oxford",
        "History",
        "History Of the UK"
    );

    public final static Human personLucyGreen = new Human(
        new FullName("Lucy", "", "Green"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "English"
    );
    public final static Student studentLucyGreen = new Student(
        personLucyGreen,
        "Cambridge",
        "Physics",
        "Quantum Mechanics"
    );

    public final static Human personArielGreen = new Human(
        new FullName("Ariel", "", "Green"),
        LocalDate.of(2000, 5, 12),
        "Female",
        "Scottish"
    );
    public final static Student studentArielGreen = new Student(
        personArielGreen,
        "Cambridge",
        "Linguistics",
        "English Literature"
    );


    public final static Human personAnnetBeaumarchais = new Human(
        new FullName("Annet", "", "Beaumarchez"),
        LocalDate.of(2013, 1, 11),
        "Female",
        "French"
    );

    public final static Human personNicolasBeau = new Human(
        new FullName("Nicolas", "", "Beau"),
        LocalDate.of(2013, 1, 11),
        "Male",
        "French"
    );
}
