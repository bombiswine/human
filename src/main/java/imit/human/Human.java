package imit.human;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Human implements Comparable<Human> {
    protected FullName    fullName;
    protected LocalDate   birthDate;
    protected HumanGender gender;
    protected String      nationality;

    public Human(
        final FullName  fullName,
        final LocalDate birthDate,
        final String    gender,
        final String    nationality
    ) {
        if (fullName == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as full name argument"
            );
        }
        if (birthDate == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as birth date argument"
            );
        }
        if (gender == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as human gender argument"
            );
        }
        if (nationality == null || nationality.isEmpty()) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as nationality argument"
            );
        }

        this.fullName    = fullName;
        this.birthDate   = birthDate;
        this.gender      = HumanGender.getGenderByValue(gender);
        this.nationality = nationality;
    }

    public Human(final Human person) {
        if (person == null) {
            throw new IllegalArgumentException(
                "The null-ref passed as Human argument into Human's copy constructor"
            );
        }

        fullName    = new FullName(person.getFullName());
        birthDate   = person.getBirthDate();
        gender      = person.getGender();
        nationality = person.getNationality();
    }

    public FullName getFullName() {
        return fullName;
    }

    public String getName() {
        return fullName.getFirstName();
    }

    public String getMiddleName() {
        return fullName.getMiddleName();
    }

    public String getSurname() {
        return fullName.getSurname();
    }

    public String getFullNameAsString() {
        return getFullName().toString();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public HumanGender getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Human human)) {
            return false;
        }

        return getFullName().equals(human.getFullName())
                   && getBirthDate().equals(human.getBirthDate())
                   && getGender() == human.getGender()
                   && getNationality().equals(human.getNationality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getFullName(),
            getBirthDate(),
            getGender(),
            getNationality()
        );
    }

    @Override
    public String toString() {
        final StringBuilder humanInfoFormatString = new StringBuilder();
        humanInfoFormatString.append("full name: %s\n").
            append("date of birth: %s\n").
            append("sex: %s\n").
            append("nationality: %s\n").
            append("age: %d\n");

        return String.format(
            humanInfoFormatString.toString(),
            fullName, birthDate, gender, nationality, getAge()
        );
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    static final int ADULT_AGE = 18;

    public boolean isAdult() {
        return getAge() >= ADULT_AGE;
    }

    @Override
    public int compareTo(final Human otherPerson) {
        return Integer.compare(getAge(), otherPerson.getAge());
    }
}