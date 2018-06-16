package com.iuminov.model;

import com.iuminov.annotations.Id;
import com.iuminov.annotations.Name;
import com.iuminov.annotations.Price;

public class Product<T extends Product<T>> implements Comparable<T> {

    @Id
    private Long id;

    @Name
    private String name;

    @Price
    private Double price;

    public Product() {
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Product p) {
        char a = this.name.toLowerCase().charAt(0);
        char b = p.name.toLowerCase().charAt(0);
        return Character.compare(a, b);
    }
}
