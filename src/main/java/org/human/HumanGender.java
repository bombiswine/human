package org.human;

public enum HumanGender {
    MALE("M"),
    FEMALE("F");

    private final String gender;
    HumanGender(final String gender) {
        this.gender = gender;
    }

    public static boolean contains(final String humanGenderString) {
        for (var humanGender : values()) {
            if (humanGenderString.equals(humanGender.gender)) {
                return true;
            }
        }

        return false;
    }
}
