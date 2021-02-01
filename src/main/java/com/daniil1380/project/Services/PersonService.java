package com.daniil1380.project.Services;


import com.daniil1380.project.Repository.PersonRepository;
import com.daniil1380.project.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository repo;

    public void save(PersonEntity customer) {
        repo.save(customer);
    }

    public List<PersonEntity> listAll() {
        return (List<PersonEntity>) repo.findAll();
    }

    public PersonEntity get(String name) {
        return repo.findById(name).get();
    }

    public List<PersonEntity> listByNameStartingWithAndGender(String name, String gender) {
        return (List<PersonEntity>) repo.findByNameStartingWithAndGender(name, gender);
    }

    public void delete(String name) {
        repo.deleteById(name);
    }
}
