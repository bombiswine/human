package imit.student;

import imit.human.FullName;
import imit.human.Human;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class Student extends Human {
    private String university;
    private String faculty;
    private String specialization;

    public Student(
        final FullName fullName,
        final LocalDate birthDate,
        final String gender,
        final String nationality,
        final String university,
        final String faculty,
        final String specialization
    ) {
        super(fullName, birthDate, gender, nationality);
        if (faculty == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to Student constructor as faculty-name"
            );
        }
        if (faculty.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to Student constructor as faculty-name"
            );
        }
        this.university = university;
        this.faculty = faculty;
        this.specialization = specialization;
    }

    public Student(
        final Human person,
        final String university,
        final String faculty,
        final String specialization
    ) {
        super(person);
        final Predicate<String> notEmptyNotBlankNotTooLongString = s -> !s.isEmpty() && !s.isBlank() && s.length() < 200;
        Optional.of(university)
            .filter(Objects::nonNull)
            .filter(notEmptyNotBlankNotTooLongString)
            .map(String::toLowerCase)
            .orElseThrow(() -> new IllegalArgumentException("Error: University string is invalid"));

        Optional.of(faculty)
            .filter(Objects::nonNull)
            .filter(notEmptyNotBlankNotTooLongString)
            .map(String::toLowerCase)
            .orElseThrow(() -> new IllegalArgumentException("Error: faculty string is invalid"));

        Optional.of(specialization)
            .filter(Objects::nonNull)
            .filter(notEmptyNotBlankNotTooLongString)
            .map(String::toLowerCase)
            .orElseThrow(() -> new IllegalArgumentException("Error: specialization string is invalid"));
    }

    public Student(final Student otherStudent) {
        super(otherStudent);
        university = otherStudent.getUniversity();
        faculty = otherStudent.getFaculty();
        specialization = otherStudent.getSpecialization();
    }

    public String getFaculty() {
        return faculty;
    }

    public String getUniversity() {
        return university;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setFaculty(final String newFaculty) {
        if (newFaculty == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to setFaculty as new-faculty-name"
            );
        }
        if (newFaculty.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to setFaculty as new-faculty-name"
            );
        }
        if (!faculty.equals(newFaculty)) {
            faculty = newFaculty;
        }
    }

    public void setUniversity(final String newUniversity) {
        if (newUniversity == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to setUniversity"
            );
        }
        if (newUniversity.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to setUniversity"
            );
        }
        if (!university.equals(newUniversity)) {
            university = newUniversity;
        }
    }

    public void setSpecialization(final String newSpecialization) {
        if (newSpecialization == null) {
            throw new IllegalArgumentException(
                "Null-ref passed to setSpecialization"
            );
        }
        if (newSpecialization.isEmpty()) {
            throw new IllegalArgumentException(
                "The empty string passed to setSpecialization"
            );
        }
        if (!specialization.equals(newSpecialization)) {
            specialization = newSpecialization;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student student)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return getUniversity().equals(student.getUniversity())
            && getFaculty().equals(student.getFaculty())
            && getSpecialization().equals(student.getSpecialization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUniversity(), getFaculty(), getSpecialization());
    }
}