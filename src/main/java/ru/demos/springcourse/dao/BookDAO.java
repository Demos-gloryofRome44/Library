package ru.demos.springcourse.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.demos.springcourse.models.Book;
import ru.demos.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Book> index() {
        return jdbcTemplate.query(
                "SELECT * FROM Book ORDER BY title",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)
        ).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO Book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear()
        );
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(
                "UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(),
                updatedBook.getYear(), id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assign(int bookId, int personId) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id=? WHERE id=?",
                personId, bookId
        );
    }

    public void release(int bookId) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id=NULL WHERE id=?",
                bookId
        );
    }

    public Optional<Person> getBookOwner(int bookId) {
        return jdbcTemplate.query(
                "SELECT p.* FROM Person p JOIN Book b ON p.id = b.person_id WHERE b.id = ?",
                new Object[]{bookId},
                new BeanPropertyRowMapper<>(Person.class)
        ).stream().findFirst();
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query(
                "SELECT * FROM Book WHERE person_id = ?",
                new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class)
        );
    }
}