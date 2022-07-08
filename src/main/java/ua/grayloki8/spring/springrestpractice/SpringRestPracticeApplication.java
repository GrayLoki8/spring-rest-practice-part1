package ua.grayloki8.spring.springrestpractice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRestPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestPracticeApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();

    }
}
