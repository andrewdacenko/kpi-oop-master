package kr_stud.dao;

import kr_stud.models.Mark;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface MarkDao extends CrudRepository<Mark, Long> {

}

