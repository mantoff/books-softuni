package bg.softuni.books;

import bg.softuni.books.model.entity.Author;
import bg.softuni.books.model.entity.Book;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BookAppInit implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookAppInit(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (bookRepository.count() == 0 && authorRepository.count() == 0) {
            initKing();
            initOrwell();
            initAsimov();
        }
    }

    private void initAuthor(String name, String... books) {
        Author author = new Author();
        author.setName(name);
        author = authorRepository.save(author);

        List<Book> allBooks = new ArrayList<>();

        for (String book : books) {
            Book aBook = new Book();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            aBook.setIsbn(UUID.randomUUID().toString());
            allBooks.add(aBook);
        }

        author.setBooks(allBooks);
        authorRepository.save(author);

        bookRepository.saveAll(allBooks);
    }

    private void initAsimov() {
        initAuthor("Isaac Asimov",
                "The Naked Sun");
    }

    private void initOrwell() {
        initAuthor("George Orwell",
                "1984");
    }

    private void initKing() {
        initAuthor("Stephen King",
                "It", "The Green Mile", "Pet Sematary");
    }
}
