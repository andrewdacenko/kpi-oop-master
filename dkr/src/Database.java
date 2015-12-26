import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Database {

    public ArrayList<Student> students = new ArrayList<Student>();

    public Object[][] databaseResults = {
            {
                    "Dacenko Andrey",
                    "5202",
                    "5",
                    "male",
                    "Ukraine",
                    new Object[][]{{
                            "History",
                            "5"
                    }, {
                            "Math",
                            "5"
                    }}
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
                    }, {
                            "Global",
                            "5"
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

    public TableModel defaultTableModel;

    public Database() {
        for (Object[] o : databaseResults) {
            Scores scores = new Scores();

            Object[][] oScores = (Object[][]) o[5];

            for (Object[] oScore : oScores) {
                scores.add(new Score(oScore));
            }

            o[5] = scores;
            students.add(new Student(o));
        }

        columns = new Object[]{
                "Full name",
                "Student Id",
                "Course",
                "Country",
                "Gender",
                "Scores"
        };

        defaultTableModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return students.size();
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                Student s = students.get(rowIndex);

                return s.getDataByIndex(columnIndex);
            }
        };
    }
}