package ru.aston.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private List<User> users = new ArrayList<>();
    private Book book;
    private int quantity;

    public Order() {
    }

    public Order(int userId, int bookId, int quantity) {
        this.users.add(new User(userId, "", ""));
        this.book = new Book(bookId,"",0,0,0D);
        this.quantity = quantity;
    }
    
    public Order(int id, int userId, int bookId, int quantity) {
    	this.id = id;
        this.users.add(new User(userId, "", ""));
        this.book = new Book(bookId,"",0,0,0D);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return users.get(0);
    }

    public void setUser(User user) {
        this.users.add(user);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserId() {
        if (users.isEmpty()) return 0;
        return users.get(0).getId();
    }

    public int getBookId() {
        return book.getId();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }    

	@Override
	public int hashCode() {
		return Objects.hash(book, id, quantity, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(book, other.book) && id == other.id && quantity == other.quantity
				&& Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", users=" + users + ", book=" + book + ", quantity=" + quantity + "]";
	}

}
