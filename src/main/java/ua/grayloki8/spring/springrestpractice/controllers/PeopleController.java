package ua.grayloki8.spring.springrestpractice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ua.grayloki8.spring.springrestpractice.dto.PersonDTO;
import ua.grayloki8.spring.springrestpractice.models.Person;
import ua.grayloki8.spring.springrestpractice.sevices.PeopleService;
import ua.grayloki8.spring.util.PersonErrorResponse;
import ua.grayloki8.spring.util.PersonNotCreatedException;
import ua.grayloki8.spring.util.PersonNotFoundException;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMassage=new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error:fieldErrors
                 ) {
                errorMassage.append(error.getField()).append("-").append(error.getDefaultMessage()).
                        append(";");
            }
            throw new PersonNotCreatedException(errorMassage.toString());
        }
        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setAge(personDTO.getAge());
        person.setEmail(personDTO.getEmail());
        return person;
    }



    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(), System.currentTimeMillis());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }
}
