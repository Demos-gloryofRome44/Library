package ru.demos.springcourse.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.demos.springcourse.dao.BookDAO;
import ru.demos.springcourse.dao.PersonDAO;
import ru.demos.springcourse.models.Book;
import ru.demos.springcourse.models.Person;

import java.util.Optional;

@RequestMapping("/books")
@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model, @ModelAttribute("person") Person person) {
        Optional<Book> bookOptional = bookDAO.show(id);

        if (bookOptional.isEmpty()) {
            return "redirect:/books";
        }

        Book book = bookOptional.get();
        model.addAttribute("book", book);

        if (book.getPersonId() != null) {
            Optional<Person> bookOwner = bookDAO.getBookOwner(id);
            bookOwner.ifPresent(owner -> model.addAttribute("bookOwner", owner));
        } else {
            model.addAttribute("people", personDAO.index());
        }

        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<Book> bookOptional = bookDAO.show(id);

        if (bookOptional.isEmpty()) {
            return "redirect:/books";
        }

        model.addAttribute("book", bookOptional.get());
        return "books/edit";
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public String update(@ModelAttribute @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int bookId,
                         @RequestParam("personId") int personId) {
        bookDAO.assign(bookId, personId);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int bookId) {
        bookDAO.release(bookId);
        return "redirect:/books/" + bookId;
    }

}
