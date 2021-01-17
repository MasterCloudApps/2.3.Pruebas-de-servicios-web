package es.codeurjc.test.cdctesting_ejem1;

public class Book {

    public Long id;
    public String title;
    public String author;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
	}

	/**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
