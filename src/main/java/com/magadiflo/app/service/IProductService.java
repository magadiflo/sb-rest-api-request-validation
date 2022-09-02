package com.magadiflo.app.service;

import com.magadiflo.app.domain.Product;

import java.util.List;

public interface IProductService {

    List<Product> listAll();

    Product save(Product product);

}
