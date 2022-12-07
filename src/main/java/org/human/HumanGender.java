package org.human;

<<<<<<< HEAD
enum HumanGender {
=======
public enum HumanGender {
>>>>>>> master
    MALE("M"),
    FEMALE("F");

    private final String gender;
<<<<<<< HEAD

    HumanGender(String gender) {
        this.gender = gender;
    }
=======
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
>>>>>>> master
}
