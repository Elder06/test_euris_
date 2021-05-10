package com.euris.test.model;

public class Product implements org.generation.italy.javazon2.util.IMappablePro {

    private String serial_number;
    private String name;
    private String price;

    public Product(String serial_number, String name, String price) {
        this.serial_number = serial_number;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serialnumber) {
        this.serial_number = serialnumber;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "serialnumber='" + serial_number + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
