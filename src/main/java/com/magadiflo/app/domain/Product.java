package com.magadiflo.app.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @NotBlank, el elemento anotado no debe ser nulo y debe contener al menos
     * un carácter que no sea un espacio en blanco. Acepta CharSequence.
     */
    @NotBlank(message = "Product name must not be blank")
    @Length(min = 5, max = 512, message = "Product name must have 5-512 characteres")
    @Column(nullable = false, length = 512, unique = true)
    private String name;

    @Min(value = 10, message = "Product price must be greater than 9")
    @Max(value = 9999, message = "Product price must be less than 10000")
    private Double price;

    public Product() {
    }

    public Product(String name, Double price) {
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
}
