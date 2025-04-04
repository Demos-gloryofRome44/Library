package ru.demos.springcourse.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demos.springcourse.dao.BookDAO;
import ru.demos.springcourse.dao.PersonDAO;
import ru.demos.springcourse.models.Person;
import ru.demos.springcourse.validator.PersonValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {

    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final BookDAO bookDAO;

    @GetMapping
    public String index(Model model) {
        List<Person> people = personDAO.index();

        Map<Integer, Integer> booksCount = new HashMap<>();
        for (Person person : people) {
            booksCount.put(person.getId(), bookDAO.getBooksByPersonId(person.getId()).size());
        }

        model.addAttribute("people", people);
        model.addAttribute("booksCount", booksCount);

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
