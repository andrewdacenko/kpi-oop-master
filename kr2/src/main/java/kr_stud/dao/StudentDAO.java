package kr_stud.dao;

import kr_stud.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface StudentDao extends CrudRepository<Student, Long>{

    @Query("SELECT s FROM Student s, Mark m WHERE s.course = 2 AND s.id = m.student.id GROUP BY s.id HAVING AVG(m.mark) >= 5.0")
    public List<Student> findBestOfSecondCourse();

    @Query("SELECT s FROM Student s, Mark m WHERE s.id = m.student.id AND s.country.name not like '%Ukraine%' GROUP BY s.id HAVING AVG(m.mark) >= 4.0")
    public List<Student> findGoodForeign();
}
