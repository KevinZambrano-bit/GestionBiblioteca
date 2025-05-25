package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String editorial;
    private String category;
    private int yearPublication;
    private boolean available;

    public Book() {}

//    public Book(int id, String title, String author, String editorial, String category, int yearPublication, boolean available) {
//        this.id = id;
//        this.title = title;
//        this.author = author;
//        this.editorial = editorial;
//        this.category = category;
//        this.yearPublication = yearPublication;
//        this.available = available;
//
//    }

    //Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    public int getYearPublication() {
        return yearPublication;
    }
    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
