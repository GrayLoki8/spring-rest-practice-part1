package ua.grayloki8.spring.springrestpractice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.grayloki8.spring.springrestpractice.models.Person;
@Repository
public interface PeopleRepository extends JpaRepository<Person,Integer> {

}
