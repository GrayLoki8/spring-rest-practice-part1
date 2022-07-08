package ua.grayloki8.spring.springrestpractice.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(message = "Size must me from 2 to 30 character")
    private String name;
    @Min(value = 0,message = "Age must be greater than 0")

    private int age;
    @Email
    private String email;

    public PersonDTO() {
    }

    public PersonDTO(@NotEmpty(message = "Name should not be empty") @Size(message = "Size must me from 2 to 30 character") String name, @Min(value = 0, message = "Age must be greater than 0") int age, @Email String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
