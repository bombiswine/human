package org.human;

public enum HumanGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;
    HumanGender(final String gender) {
        this.gender = gender;
    }
}
