package imit.human;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;

public class Human implements Comparable<Human>, Serializable {
    protected FullName    fullName;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    protected LocalDate   birthDate;
    protected HumanGender gender;
    protected String      nationality;

    @JsonCreator
    public Human(
        final @JsonProperty("fullName") FullName fullName,
        final @JsonProperty("birthDate") LocalDate birthDate,
        final @JsonProperty("gender") String gender,
        final @JsonProperty("nationality") String nationality
    ) {
        Objects.requireNonNull(
            fullName,"The null passed into Human's constructor as full name argument"
        );
        Objects.requireNonNull(
            birthDate, "The null passed into Human's constructor as birth date argument"
        );
        Objects.requireNonNull(
            gender,  "The null passed into Human's constructor as human gender argument"
        );
        Objects.requireNonNull(
            gender,  "The null passed into Human's constructor as human gender argument"
        );
        Objects.requireNonNull(
            nationality,  "The null passed into Human's constructor as nationality argument"
        );

        this.fullName    = fullName;
        this.birthDate   = birthDate;
        this.gender      = HumanGender.getGenderByValue(gender);
        this.nationality = Optional.of(nationality)
                            .filter(s -> !s.isEmpty())
                            .orElseThrow(() -> new IllegalArgumentException(
                                "The empty string passed as nationality into Human's constructor"
                            ));
    }

    public Human(final Human person) {
        Objects.requireNonNull(
            person, "The null-ref passed as Human argument into Human's copy constructor"
        );

        fullName    = new FullName(person.getFullName());
        birthDate   = person.getBirthDate();
        gender      = person.getGender();
        nationality = person.getNationality();
    }

    @JsonGetter("fullName")
    public FullName getFullName() {
        return fullName;
    }

    @JsonIgnore
    public String getName() {
        return fullName.getFirstName();
    }

    @JsonIgnore
    public String getMiddleName() {
        return fullName.getMiddleName();
    }

    @JsonIgnore
    public String getSurname() {
        return fullName.getSurname();
    }

    @JsonIgnore
    public String getFullNameAsString() {
        return getFullName().toString();
    }

    @JsonGetter("birthDate")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @JsonGetter("gender")
    public HumanGender getGender() {
        return gender;
    }

    @JsonGetter("nationality")
    public String getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Human human)) {
            return false;
        }

        return getFullName().equals(human.getFullName())
                   && getBirthDate().equals(human.getBirthDate())
                   && getGender() == human.getGender()
                   && getNationality().equals(human.getNationality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFullName(), getBirthDate(), getGender(), getNationality());
    }

    @Override
    public String toString() {
        return new StringBuffer("Human{")
            .append("fullName=").append(fullName)
            .append(", birthDate=").append(birthDate)
            .append(", gender=").append(gender)
            .append(", nationality='").append(nationality).append('\'')
            .append('}')
            .toString();
    }

    @JsonIgnore
    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    static final int ADULT_AGE = 18;

    @JsonIgnore
    public boolean isAdult() {
        return getAge() >= ADULT_AGE;
    }

    @Override
    public int compareTo(final Human otherPerson) {
        return Integer.compare(getAge(), otherPerson.getAge());
    }
}