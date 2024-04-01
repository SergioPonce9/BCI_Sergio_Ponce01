package cl.bci;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DataAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAuthApplication.class, args);
        log.info(".::: Init back-end DataAuth :::.");
        log.error("TEST");
    }

}
