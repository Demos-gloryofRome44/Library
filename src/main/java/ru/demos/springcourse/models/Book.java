package ru.demos.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private int id;

    @NotEmpty(message = "The name must not be empty")
    @Size(min = 2, max = 100, message = "The name must be between 2 and 100 characters.")
    private String title;

    @NotEmpty(message = "Author must not be empty")
    @Size(min = 2, max = 100, message = "The author's name must be between 2 and 100 characters.")
    private String author;

    @Min(value = 0, message = "The year must be positive")
    private int year;

    private Integer personId;
}
