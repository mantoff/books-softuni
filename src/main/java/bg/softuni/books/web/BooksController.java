package bg.softuni.books.web;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {

        Optional<BookDTO> bookOpt = bookService.getBookById(bookId);

        if(bookOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookOpt.get());
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> allBooks = bookService.getAllBooks();

        if(allBooks.size() == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allBooks);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook,
                                              UriComponentsBuilder uriComponentsBuilder) {
        long newBookID = bookService.createBook(newBook);

        return
                ResponseEntity.
                        created(uriComponentsBuilder.path("/api/books/{id}").
                                build(newBookID)).
                        build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId) {
        bookService.deleteBookById(bookId);

        return ResponseEntity.
                noContent().
                build();
    }
}
