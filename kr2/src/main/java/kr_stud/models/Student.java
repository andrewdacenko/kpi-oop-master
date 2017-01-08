package kr_stud.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "course")
    private int course;

    @NotNull
    @Column(name = "gender")
    private int gender;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @Column(name = "identity", unique = true)
    private String identity;

    @OneToMany(mappedBy = "student")
    private List<Mark> marks;

    public Student() {
    }

    public Student(long id) {
        this.id = id;
    }

    public Student(String firstName,
                   String lastName,
                   String middleName,
                   int course,
                   int gender,
                   Country country,
                   String identity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.course = course;
        this.gender = gender;
        this.country = country;
        this.identity = identity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    @JsonProperty(value = "country_id")
    public long getCountryId() {
        return country.getId();
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @JsonProperty(value = "score")
    public double getScore() {
        if(marks != null) {
            OptionalDouble average = marks
                    .stream()
                    .mapToDouble(Mark::getMark)
                    .average();

            return average.isPresent() ? average.getAsDouble() : 0;
        }

        return 0;
    }
}
