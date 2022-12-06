package org.human;

enum HumanGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;

    HumanGender(String gender) {
        this.gender = gender;
    }
}
