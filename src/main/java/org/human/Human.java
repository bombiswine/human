package org.human;

import org.human.utilities.FullName;
import org.human.utilities.HumanGender;
import org.simple_date.SimpleDate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Human {
    protected FullName   fullName;
    protected SimpleDate  birthDate;
    protected HumanGender gender;
    protected String      nationality;
    protected int         height;
    protected int         weight;

    public Human(
        final FullName   fullName,
        final SimpleDate birthDate,
        final String     gender,
        final String     nationality,
        final int        height,
        final int        weight
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
        if (!HumanGender.contains(gender)) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as human gender argument"
            );
        }
        if (nationality == null || nationality.isEmpty()) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as nationality argument"
            );
        }
        if (height <= 0) {
            throw new IllegalArgumentException(
                "A negative height value passed into Human's constructor"
            );
        }
        if (weight <= 0) {
            throw new IllegalArgumentException(
                "A negative weight value passed into Human's constructor"
            );
        }

        this.fullName    = fullName;
        this.birthDate   = birthDate;
        this.gender      = HumanGender.getGenderByValue(gender);
        this.height      = height;
        this.weight      = weight;
        this.nationality = nationality;
    }

    public FullName getFullName() {
        return fullName;
    }

    public SimpleDate getBirthDate() {
        return birthDate;
    }

    public HumanGender getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Human human)) {
            return false;
        }

        return getHeight() == human.getHeight()
            && getWeight() == human.getWeight()
            && getFullName().equals(human.getFullName())
            && getBirthDate().equals(human.getBirthDate())
            && getGender() == human.getGender()
            && getNationality().equals(human.getNationality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            getFullName(), getBirthDate(), getGender(),
            getNationality(), getHeight(), getWeight()
        );
    }

    @Override
    public String toString() {
        StringBuffer humanInfoFormatString = new StringBuffer();
        humanInfoFormatString.
            append("full name: %s\n").
            append("date of birth: %s\n").
            append("sex: %s\n").
            append("nationality: %s\n").
            append("height: %s\n").
            append("weight: %s");

        return String.format(
            humanInfoFormatString.toString(),
            fullName, birthDate, gender, nationality, height, weight
        );
    }


    public int getAge() {
        final LocalDate birthDay = LocalDate.of(
            getBirthDate().getYear(),
            getBirthDate().getMonth(),
            getBirthDate().getDay()
        );

        return Period.between(birthDay, LocalDate.now()).getYears();
    }

    public boolean isAdult() {
        final int ADULT_AGE = 18;
        return getAge() >= ADULT_AGE;
    }
}
