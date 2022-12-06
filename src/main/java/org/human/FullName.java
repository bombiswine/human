package org.human;

class FullName {
    private final String firstName;
    private final String middleName;
    private final String surname;

    public FullName(
        final String firstName,
        final String middleName,
        final String surname
    ) {
        if (firstName == null || firstName.isEmpty() ) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                "FullName's constructor as first name"
            );
        }
        if (middleName == null || middleName.isEmpty() ) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                "FullName's constructor as middle name"
            );
        }
        if (surname == null || surname.isEmpty() ) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                "FullName's constructor as surname"
            );
        }

        this.firstName  = firstName;
        this.middleName = middleName;
        this.surname    = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName(final FullNameFormats format) {
        StringBuffer fullName = new StringBuffer();
        switch (format){
            case SURNAME_FIRSTNAME_MIDDLENAME:
                fullName.append(surname).
                         append(" ").
                         append(middleName).
                         append(" ").
                         append(firstName);

            case FIRSTNAME_MIDDLENAME_SURNAME:
                fullName.append(firstName).
                         append(" ").
                         append(middleName).
                         append(" ").
                         append(surname);
        }

        return fullName.toString();
    }

    private enum FullNameFormats {
        SURNAME_FIRSTNAME_MIDDLENAME("SFM"),
        FIRSTNAME_MIDDLENAME_SURNAME("FMS");

        private final String fullNameFormat;

        FullNameFormats(final String fullNameFormat) {
            this.fullNameFormat = fullNameFormat;
        }
    }
}
