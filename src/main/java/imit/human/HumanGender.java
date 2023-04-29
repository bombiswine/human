package imit.human;

public enum HumanGender {
    MALE("male"),
    FEMALE("female");

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

    public static HumanGender getGenderByValue(final String gender) {
        if (!contains(gender.toLowerCase())) {
            throw new IllegalArgumentException(
                "The unknown gender was passed as String gender into HumanGender.HumanGender()"
            );
        }
        if (gender.equalsIgnoreCase("male")) {
            return MALE;
        }
        return FEMALE;
    }

    @Override
    public String toString() {
        return gender;
    }
}
