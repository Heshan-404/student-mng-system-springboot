package edu.icet.crm.repository;

import edu.icet.crm.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaRepository extends CrudRepository<StudentEntity, Integer> {
}
