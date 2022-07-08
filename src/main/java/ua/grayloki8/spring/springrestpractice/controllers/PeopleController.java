package ua.grayloki8.spring.springrestpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.grayloki8.spring.springrestpractice.models.Person;
import ua.grayloki8.spring.springrestpractice.sevices.PeopleService;
import ua.grayloki8.spring.util.PersonErrorResponse;
import ua.grayloki8.spring.util.PersonNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private PeopleService peopleService;
    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
    @GetMapping()
    public List<Person> getPeople(){
        return peopleService.findAll();
    }
    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id")int id){
        return     peopleService.findOne(id);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse("Person with this id wasn't found", System.currentTimeMillis());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);

    }
}
