package com.daniil1380.project;

import com.daniil1380.project.Services.FunctionService;
import com.daniil1380.project.Services.PersonService;
import com.daniil1380.project.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ContextResource;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

    @Autowired
    FunctionService service;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ProjectApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.start(args);
    }
}
