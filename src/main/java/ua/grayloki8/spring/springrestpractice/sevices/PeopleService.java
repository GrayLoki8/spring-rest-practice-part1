package ua.grayloki8.spring.springrestpractice.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.grayloki8.spring.springrestpractice.models.Person;
import ua.grayloki8.spring.springrestpractice.repositories.PeopleRepository;
import ua.grayloki8.spring.util.PersonNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

   private PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Person findOne(int id){
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public void save(Person person){
        peopleRepository.save(person);

    }
}
