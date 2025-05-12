package model;

public class Book {
    public int id;
    public String name;
    public String author;
    public String genre;
    public String description;

    public Book(int id, String name, String author, String genre, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.description = description;
    }
}