package ru.demos.springcourse.validator;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.demos.springcourse.dao.PersonDAO;
import ru.demos.springcourse.models.Person;

@RequiredArgsConstructor
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {
        Person person = (Person) o;

        personDAO.getPersonByName(person.getName())
                .ifPresent(p -> e.rejectValue("name", "", "Человек с таким именем уже существует"));
    }
}
