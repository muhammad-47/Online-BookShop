package com.example.onlinebookshop;

public class Book {

    String name;
    String des;
    String author;
    String ImageUrl;
    String Price;

    public Book() {
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Book(String name, String des, String author, String price, String imageUrl) {
        this.name = name;
        this.des = des;
        this.author = author;
        ImageUrl = imageUrl;
        this.Price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}