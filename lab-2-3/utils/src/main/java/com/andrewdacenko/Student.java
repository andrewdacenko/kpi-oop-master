package com.andrewdacenko;

import java.io.Serializable;

public class Student implements Serializable {
    public static final String[] HEADER = {
            "First name",
            "Last name",
            "Middle name",
            "ID",
            "Course",
            "Country",
            "Gender",
            "Mark"
    };

    public static final String[] FIELDS = {
            "first_name",
            "last_name",
            "middle_name",
            "id",
            "course",
            "country",
            "gender",
            "mark"
    };

    public static final String[] COURSES = {
            "1", "2", "3", "4", "5", "6"
    };

    public static final String[] COUNTRIES = {
            "USA", "Ukraine", "Russia", "Poland", "Great Britain", "Zimbabwe"
    };

    public static final String[] MARKS = {
            "1", "2", "3", "4", "5"
    };

    public static final String[] GENDERS = {"male", "female"};

    private String last_name;
    private String first_name;
    private String middle_name;
    private String id;
    private String course;
    private String country;
    private String gender;
    private double mark;

    public Student(
            String last_name,
            String first_name,
            String middle_name,
            String id,
            String course,
            String country,
            String gender,
            double mark
    ) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.id = id;
        this.course = course;
        this.country = country;
        this.gender = gender;
        this.mark = mark;
    }

    public Student() {
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return course;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getElementByIndex(int index) {
        switch (index) {
            case 0: {
                return this.first_name;
            }
            case 1: {
                return this.last_name;
            }
            case 2: {
                return this.middle_name;
            }
            case 3: {
                return this.id;
            }
            case 4: {
                return this.course;
            }
            case 5: {
                return this.country;
            }
            case 6: {
                return this.gender;
            }
            case 7: {
                return String.valueOf(this.mark);
            }
            default: {
                return null;
            }
        }
    }
}
