package com.daniil1380.project.Repository;

import com.daniil1380.project.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {

    Iterable<PersonEntity> findByNameStartingWithAndGender(String name, String gender);
}
