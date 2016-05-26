package com.andrewdacenko;

import java.util.ArrayList;
import java.util.List;

public class StoreDatabase {
    private static final String DATABASE_FILE = "students.db";

    public ArrayList<Student> findAll() {
        return (ArrayList<Student>)SerializationUtil.deserialize(DATABASE_FILE);
    }

    public ArrayList<Student> add(Student student) {
        ArrayList<Student> students = findAll();
        students.add(student);
        SerializationUtil.serialize(students, DATABASE_FILE);
        return findAll();
    }

    public ArrayList<Student> update(ArrayList<Student> students) {
        SerializationUtil.serialize(students, DATABASE_FILE);
        return findAll();
    }

    public ArrayList<Student> getSecondBest() {
        ArrayList<Student> secondCourseBestStudents = new ArrayList<>();

        findAll().forEach(p -> {
            if (p.getMark() > 4 && p.getCourse().equals("2")) {
                secondCourseBestStudents.add(p);
            }
        });

        return secondCourseBestStudents;
    }

    public ArrayList<Student> getGoodForeign() {
        ArrayList<Student> goodForeignStudents = new ArrayList<>();

        findAll().forEach(p -> {
            if (p.getMark() > 3 && !p.getCountry().equals("Ukraine")) {
                goodForeignStudents.add(p);
            }
        });

        return goodForeignStudents;
    }
}
