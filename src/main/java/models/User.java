package models;

public class User {
    private int id;
    private String name;
    private String email;
    private String userType;
    private String phone;

    public User() {}

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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
