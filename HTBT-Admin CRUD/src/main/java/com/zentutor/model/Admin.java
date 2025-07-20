package com.zentutor.model;

//the attributes of an admin
public abstract class Admin {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    //Admin overloaded constructor
    public Admin(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //getters
    public  int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    //setters
    public void setRole(String role) {
        this.role = role;
    }
}