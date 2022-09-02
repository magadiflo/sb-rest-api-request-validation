package com.magadiflo.app.setup;

import com.magadiflo.app.domain.Product;
import com.magadiflo.app.repository.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoaderSetup {

    private final IProductRepository productRepository;

    public DataLoaderSetup(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            this.productRepository.deleteAll();
            Product p1 = new Product("Impresora Epson L350", 750.50);
            Product p2 = new Product("Celular Huawei", 920.00);
            this.productRepository.saveAll(Arrays.asList(p1, p2));
            System.out.println("Products added to database!!!");
        };
    }

}
