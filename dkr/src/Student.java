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

    public Object getDataByIndex(int index) {
        switch (index) {
            case 0:
                return name;
            case 1:
                return studentId;
            case 2:
                return course;
            case 3:
                return gender;
            case 4:
                return country;
            default:
                return scores;
        }
    }
}
