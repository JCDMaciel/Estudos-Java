package org.example.checkout.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "width")
    private double width;

    @Column(name = "height")
    private double height;

    @Column(name = "length")
    private double length;

    @Column(name = "weight")
    private double weight;

    @Column(name = "currency")
    private String currency;

    public Product() {
        // Default constructor required by JPA
    }

    public Product(int idProduct, String description, double price, double width, double height, double length, double weight, String currency) {
        if (width <= 0 || height <= 0 || length <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Invalid dimension");
        }
        this.idProduct = idProduct;
        this.description = description;
        this.price = price;
        this.width = width;
        this.height = height;
        this.length = length;
        this.weight = weight;
        this.currency = currency;
    }

    public Product(int idProduct, double price, String currency) {
        this(idProduct, null, price, 0, 0, 0, 0, currency);
    }

    public double getVolume() {
        return width / 100 * height / 100 * length / 100;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
