package com.polarbookshop.catalogservice.domain;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;  //constructor autowiring

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public Iterable<Book> viewBookList(){
        return bookRepository.findAll();
    }

    //trying to view a book that does not exist, a dedicated exception is thrown
    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn)
.orElseThrow(() -> new BookNotFoundException(isbn));
    }

    // adding the same book to the catalog multiple times,dedicated exception is thrown
    public Book addBookToCatalog(Book book) {
        if (bookRepository.existsByIsbn(book.isbn())) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }
    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    // when editing the book, all Book fields can be updated except the ISBN code, because it is the entity identifier

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
                    var bookToUpdate = new Book(
                            existingBook.id(),
                            existingBook.createdDate(),
                            existingBook.lastModifiedDate(),
                            existingBook.isbn(),
                            book.title(),
                            book.author(),
                            book.price(),
                            existingBook.version());
                    return bookRepository.save(bookToUpdate);
                })   //When changing the details for a book not in the catalog yet, create a  new book
                .orElseGet(() -> addBookToCatalog(book));
    }


}
