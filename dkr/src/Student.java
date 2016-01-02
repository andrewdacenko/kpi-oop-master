import java.util.Objects;

public class Student {

    String name;
    int studentId;
    int course;
    String gender;
    String country;
    Scores scores;

    public Student(Object[] record) throws NumberFormatException {
        name = (String) record[0];
        studentId = Integer.parseInt((String) record[1]);
        course = Integer.parseInt((String) record[2]);
        gender = (String) record[3];
        country = (String) record[4];
        scores = (Scores) record[5];
    }

    public boolean isBest() {
        boolean isSecondCourse = course == 2;
        boolean isOnlyA = Math.abs(scores.getAverage() - 5) <= 0.0001d;

        return isSecondCourse && isOnlyA;
    }

    public boolean isGood() {
        return scores.getAverage() >= 4.0d;
    }
}
