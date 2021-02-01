package com.daniil1380.project.Services;

import com.daniil1380.project.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FunctionService {

    @Autowired
    PersonService personService;

    public void start(String[] args) throws Exception {
        if (args.length == 0) throw new Exception("Not enough args");
        int function = Integer.parseInt(args[0]);
        switch (function){
            case 1 -> createTable(args);
            case 2 -> addPerson(args);
            case 3 -> allPersons(args);
            case 4 -> createRandom(args);
            case 5 -> findByNameAndGender(args);
        }

    }


    private void createTable(String[] args) throws Exception {
        if (args.length > 1) throw new Exception("Too many args");
        try {
            String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false&serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, "root", "root")) {
                Statement statement = connection.createStatement();
                statement.execute("Create table person (name char(30), birth char(30), gender char)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    private void addPerson(String[] args) throws Exception {
        if (args.length != 4) throw new Exception("Not enough or too many args");
        PersonEntity personEntity = new PersonEntity(args[1], args[2], args[3]);
        personService.save(personEntity);
    }

    private void allPersons(String[] args) throws Exception {
        if (args.length > 1) throw new Exception("Too many args");
        Set<PersonEntity> set = personService.listAll().stream()
                .sorted(Comparator.comparing(PersonEntity::getName))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        for(PersonEntity personEntity: set){
            System.out.println(personEntity.toString());
        }
    }

    private void createRandom(String[] args) throws Exception {
        if (args.length > 1) throw new Exception("Too many args");
        for (int i = 0; i < 100; i++) {
            PersonEntity personEntity = new PersonEntity();
            personService.save(personEntity);
        }
        for (int i = 0; i < 100; i++) {
            PersonEntity personEntity = new PersonEntity('F', "M");
            personService.save(personEntity);
        }
    }

    private void findByNameAndGender(String[] args) throws Exception {
        if (args.length > 1) throw new Exception("Too many args");
        List<PersonEntity> list = personService.listByNameStartingWithAndGender("F", "M");
        for (PersonEntity personEntity : list) {
            System.out.println(personEntity.toString());
        }
    }



}
