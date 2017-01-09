package kr_stud.dao;

import kr_stud.models.Subject;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SubjectDao extends CrudRepository<Subject, Long> {

}

