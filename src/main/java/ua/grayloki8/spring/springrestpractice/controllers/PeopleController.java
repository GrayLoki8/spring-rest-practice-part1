package ua.grayloki8.spring.springrestpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.grayloki8.spring.springrestpractice.models.Person;
import ua.grayloki8.spring.springrestpractice.sevices.PeopleService;

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
}
