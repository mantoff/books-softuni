package bg.softuni.books.model.dto;

public class BookDTO {

    private Long id;

    public AuthorDTO getAuthor() {
        return author;
    }

    public BookDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }

    private String title;
    private AuthorDTO author;
    private String isbn;

    public BookDTO() {
    }

    public String getTitle() {
        return title;
    }

    public BookDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookDTO setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookDTO setId(Long id) {
        this.id = id;
        return this;
    }
}

