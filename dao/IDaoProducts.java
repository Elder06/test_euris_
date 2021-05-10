package com.euris.test.dao;

import com.euris.test.model.Product;

import java.util.List;

public interface IDaoProducts {

    List<Product>products();

    Product product (String serial_number);

    boolean add(Product p);

    boolean delete(String serial_number);

    boolean update(Product p);

    public String price(String serial_number);

    public String sum(String serial_number, String serial_B);

    public String diff(String serial_number, String serial_B);

    public String multy(String serial_number, int multiplier);

    public String div(String serial_number, int divider);

}
