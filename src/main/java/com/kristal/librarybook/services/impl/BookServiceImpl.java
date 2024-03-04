package com.kristal.librarybook.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kristal.librarybook.domain.BookEntity;
import com.kristal.librarybook.repositories.BookRepository;
import com.kristal.librarybook.services.BookServices;

@Service
public class BookServiceImpl implements BookServices {

    private final BookRepository bookRepository;

    // Constructor injection of BookRepository
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Method to create a new book
    @Override
    public BookEntity createBook(BookEntity book) {
        if (book != null) {
            return bookRepository.save(book);
        }
        return null;
    }

    // Method to retrieve all books
    @Override
    public List<BookEntity> getAllBook() {
        return bookRepository.findAll();
    }

    // Method to retrieve a book by its ISBN
    @Override
    public Optional<BookEntity> getBookById(String isbn) {
        if (!isbn.isEmpty()) {
            return bookRepository.findById(isbn);
        }
        return Optional.empty();
    }

    // Method to delete a book by its ISBN
    @Override
    public void deleteBookById(String isbn) {
        if (!isbn.isEmpty()) {
            bookRepository.deleteById(isbn);
        }
    }

    // Method to update a book by its ISBN
    @Override
    public BookEntity updateBook(String isbn, BookEntity updatedBook) {
        if (!isbn.isEmpty() && updatedBook != null) {
            Optional<BookEntity> existingBook = bookRepository.findById(isbn);
            if (existingBook.isPresent()) {
                updatedBook.setIsbn(isbn);
                return bookRepository.save(updatedBook);
            }
        }
        return null;
    }

}
