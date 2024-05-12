package ru.aston.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private int authorId;
    private int genreId;
    private double price;
    private List<Order> orders;
    
    public Book() {
    }

    public Book(String title, int authorId, int genreId, double price) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.price = price;
    }
    
    public Book(int id, String title, int authorId, int genreId, double price) {
        this.id = id;
    	this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.price = price;
    }

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

    public int getAuthor() {
        return authorId;
    }

    public void setAuthor(int author) {
        this.authorId = author;
    }

    public int getGenre() {
        return genreId;
    }

    public void setGenre(int genre) {
        this.genreId = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


}