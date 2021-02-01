package com.daniil1380.project.entity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Entity
@Table(name = "person", schema = "mysql")
public class PersonEntity {
    private String name;
    private String birth;
    private String gender;

    public PersonEntity(String name, String birth, String gender) {
        this.name = name;
        this.birth = birth;
        this.gender = gender;
    }

    public PersonEntity(char name, String gender) {
        this.name = name + createRandomName(2);
        this.birth = createRandomDate();
        this.gender = gender;
    }

    public PersonEntity() {
        this.name = createRandomName(3);
        this.gender = createRandomGender();
        this.birth = createRandomDate();
    }

    private String createRandomGender() {
        Random r = new Random();
        return r.nextInt(2) == 0 ? "M" : "F";
    }

    private String createRandomName(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < length; i++) {
            int randomLetter = r.nextInt(26) + (byte)'A';
            stringBuilder.append((char) randomLetter);
        }
        return stringBuilder.toString();
    }

    private String createRandomDate(){
        Random r = new Random();
        LocalDate date = LocalDate.now().minusDays(r.nextInt(10000));
        String[] birthDate = date.toString().split("-");
        return birthDate[2] + '.' + birthDate[1] + '.' + birthDate[0];
    }


    @Id
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "birth")
    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        LocalDate localDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        Period p = Period.between(localDate, LocalDate.now());
        int years = p.getYears();
        return "Имя: " + name + " Дата рождения: " + birth + " Пол: " + gender + " Количество лет: " + years;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(birth, that.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth);
    }
}
