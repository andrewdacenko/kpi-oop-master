public class Database {
    public Object[][] databaseResults;

    public static Object[] columns = new Object[]{
            "Full name",
            "Student Id",
            "Course",
            "Gender",
            "Country",
            "Scores"
    };

    public StudentsTableModel studentsTableModel;

    public Database() {
        databaseResults = DataGenerator.generate(256);

        studentsTableModel = new StudentsTableModel(databaseResults, columns);
    }
}