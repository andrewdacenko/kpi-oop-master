package kr_stud.dao;

import kr_stud.models.Country;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CountryDao extends CrudRepository<Country, Long>{

}
