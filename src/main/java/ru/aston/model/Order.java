package ru.aston.model;

public class Order {
    private int id;
    private User user;
    private Book book;
    private int quantity;

    public Order() {
    }

    public Order(int userId, int bookId, int quantity) {
        this.user.setId(userId);
        this.book.setId(bookId);
        this.quantity = quantity;
    }
    
    public Order(int id, int userId, int bookId, int quantity) {
    	this.id = id;
        this.user.setId(userId);
        this.book.setId(bookId);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return user.getId();
    }

    public int getBookId() {
        return book.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((book == null) ? 0 : book.hashCode());
        result = prime * result + quantity;
        return result;
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
        if (id != other.id)
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (book == null) {
            if (other.book != null)
                return false;
        } else if (!book.equals(other.book))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", user=" + user + ", book=" + book + ", quantity=" + quantity + "]";
    }

}
