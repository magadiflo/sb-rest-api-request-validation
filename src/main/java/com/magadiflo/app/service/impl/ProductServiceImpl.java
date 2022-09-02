package com.magadiflo.app.service.impl;

import com.magadiflo.app.domain.Product;
import com.magadiflo.app.repository.IProductRepository;
import com.magadiflo.app.service.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Product> listAll() {
        return this.productRepository.findAll();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return this.productRepository.save(product);
    }
}
