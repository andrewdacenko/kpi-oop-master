import javax.swing.table.DefaultTableModel;

public class Database {

    public Object[][] databaseResults = {
            {
                    "Dacenko Andrey",
                    "5202",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{
                            {
                                    "History",
                                    "5"
                            },
                            {
                                    "Math",
                                    "5"
                            }
                    }
            },
            {
                    "Misha Beherskiy",
                    "5203",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{{
                            "OOP",
                            "4"
                    }}
            },
            {
                    "Priadko Dima",
                    "5101",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{{
                            "Statistics",
                            "4"
                    }}
            },
            {
                    "Danilyuk Viktor",
                    "5100",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{{
                            "Testing",
                            "4"
                    }}
            },
            {
                    "Kiselyov Evgeniy",
                    "5108",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{{
                            "English",
                            "5"
                    }}
            },
            {
                    "Liza Ivanova",
                    "3108",
                    "3",
                    "female",
                    "Ukraine",
                    new Object[][]{{
                            "Law",
                            "3"
                    }}
            }
    };

    public Object[] columns;

    public DefaultTableModel defaultTableModel;

    public Database() {
        // Прізвище, Ім’я, По батькові, Студентський квиток, Курс, Країна, Пол, Успішність

        columns = new Object[]{
                "Full name",
                "Student Id",
                "Course",
                "Country",
                "Gender",
                "Scores"
        };

        defaultTableModel = new DefaultTableModel(databaseResults, columns) {
            public Class getColumnClass(int column) { // Override the getColumnClass method to get the
                Class classToReturn;                    // class types of the data retrieved from the database

                if ((column >= 0) && column < getColumnCount()) {
                    classToReturn = getValueAt(0, column).getClass();
                } else {
                    classToReturn = Object.class;
                }
                return classToReturn;
            }
        };
    }
}