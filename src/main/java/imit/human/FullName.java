package imit.human;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class FullName implements Serializable {
    private final String firstName;
    private final String middleName;
    private final String surname;

    @JsonCreator
    public FullName(
        final @JsonProperty("firstName") String firstName,
        final @JsonProperty("middleName") String middleName,
        final @JsonProperty("surname") String surname
    ) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                    "FullName's constructor as first name"
            );
        }
        if (middleName == null) {
            throw new IllegalArgumentException(
                "null passed into FullName's constructor as middle name"
            );
        }
        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException(
                "null or empty string passed into " +
                    "FullName's constructor as surname"
            );
        }

        this.firstName  = firstName;
        this.middleName = middleName;
        this.surname    = surname;
    }

    public FullName(final FullName anotherFullName) {
        if (anotherFullName == null) {
            throw new IllegalArgumentException(
                "null-ref passed into FullName's copying constructor"
            );
        }
        firstName  = anotherFullName.getFirstName();
        middleName = anotherFullName.getMiddleName();
        surname    = anotherFullName.getSurname();
    }

    @JsonGetter("surname")
    public String getSurname() {
        return surname;
    }

    @JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonGetter("middleName")
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FullName fullName)) {
            return false;
        }

        return getFirstName().equals(fullName.getFirstName())
                   && getMiddleName().equals(fullName.getMiddleName())
                   && getSurname().equals(fullName.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getMiddleName(), getSurname());
    }

    @Override
    public String toString() {
        return new StringBuffer("FullName{")
            .append("surname='").append(surname).append('\'')
            .append(", firstName='").append(firstName).append('\'')
            .append(", middleName='").append(middleName).append('\'')
            .append('}')
            .toString();
    }
}
