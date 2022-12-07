package org.human;

public enum HumanGender {
    MALE("Male"),
    FEMALE("Female");

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

    public static HumanGender getGenderByValue(String gender) {
        if (gender.equals("Male")) {
            return MALE;
        }
        return FEMALE;
    }

    @Override
    public String toString() {
        return gender;
    }
}
