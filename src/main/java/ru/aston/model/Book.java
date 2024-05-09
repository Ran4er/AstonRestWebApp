package ru.aston.model;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private int author_id;
    private int genre_id;
    private double price;
    private List<Order> orders;
    static int counter = 1;
    
    public Book() {
    }

    public Book(String title, int author_id, int genre_id, double price) {
        this.id = counter++;
        this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
        this.price = price;
    }
    
    public Book(int id, String title, int author_id, int genre_id, double price) {
        this.id = id;
        counter = id;
    	this.title = title;
        this.author_id = author_id;
        this.genre_id = genre_id;
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
        return author_id;
    }

    public void setAuthor(int author) {
        this.author_id = author;
    }

    public int getGenre() {
        return genre_id;
    }

    public void setGenre(int genre) {
        this.genre_id = genre;
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