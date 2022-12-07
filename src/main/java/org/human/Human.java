package org.human;

import org.simple_date.SimpleDate;

public class Human {
    private FullName    fullName;
    private SimpleDate  birthDate;
    private HumanGender gender;
    private String      nationality;
    private int         height;
    private int         weight;

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
        if (nationality == null) {
            throw new IllegalArgumentException(
                "The null passed into Human's constructor as nationality argument"
            );
        }
        if (height < 0) {
            throw new IllegalArgumentException(
                "A negative height value passed into Human's constructor"
            );
        }
        if (weight < 0) {
            throw new IllegalArgumentException(
                "A negative weight value passed into Human's constructor"
            );
        }

        this.fullName    = fullName;
        this.birthDate   = birthDate;
        this.gender      = HumanGender.valueOf(gender);
        this.height      = height;
        this.weight      = weight;
        this.nationality = nationality;
    }
}
