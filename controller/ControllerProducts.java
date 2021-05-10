package com.euris.test.controller;


import com.euris.test.dao.IDaoProducts;
import com.euris.test.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ControllerProducts {

    @Autowired
    private IDaoProducts dao;

    @GetMapping
    public List<Product>get(){
        return dao.products();
    }

    @GetMapping("/{serial_number}")
    public Product getOne(@PathVariable String serial_number){
        return dao.product(serial_number);
    }

    @PostMapping
    public boolean post(@RequestBody Product p){
        return dao.add(p);
    }

    @PutMapping
    public boolean update(@RequestBody Product p){
        return dao.update(p);
    }

    @DeleteMapping("/{serial_number}")
    public boolean delete(@PathVariable String serial_number){
        return dao.delete(serial_number);
    }

    @GetMapping("/{serial_number}/price")
    public String price(@PathVariable String serial_number){
        return dao.price(serial_number);
    }

    @GetMapping("/sum/{serial_number}/{serial_B}")
    public String sum(@PathVariable String serial_number, @PathVariable String serial_B) {
        return dao.sum(serial_number, serial_B);
    }

    @GetMapping("/diff/{serial_number}/{serial_B}")
    public  String diff(@PathVariable String serial_number, @PathVariable  String serial_B){
        return dao.diff(serial_number, serial_B);
    }

    @GetMapping("/multy/{serial_number}/{multiplier}")
    public String multy(@PathVariable String serial_number, @PathVariable int multiplier){
        return dao.multy(serial_number, multiplier);
    }

    @GetMapping("/div/{serial_number}/{divider}")
    public  String div(@PathVariable String serial_number, @PathVariable int divider){
        return dao.div(serial_number, divider);
    }



}
