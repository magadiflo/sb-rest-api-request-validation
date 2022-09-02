package com.magadiflo.app.resource;

import com.magadiflo.app.domain.Product;
import com.magadiflo.app.service.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductResource {

    private final IProductService productService;

    public ProductResource(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> listAll() {
        return this.productService.listAll();
    }

    @PostMapping
    public Product addOne(@RequestBody Product product) {
        return this.productService.save(product);
    }


}
