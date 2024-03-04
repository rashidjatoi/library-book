package com.kristal.librarybook.services;

import java.util.List;
import java.util.Optional;

import com.kristal.librarybook.domain.BookEntity;

public interface BookServices {
    // Method to create a new book
    BookEntity createBook(BookEntity book);

    // Method to retrieve all books
    List<BookEntity> getAllBook();

    // Method to retrieve a book by its ISBN
    Optional<BookEntity> getBookById(String isbn);

    // Method to delete a book by its ISBN
    void deleteBookById(String isbn);

    // Method to update a book by its ISBN
    BookEntity updateBook(String isbn, BookEntity updatedBook);
}
