package com.fsa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "order_lines")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "quantity", columnDefinition = "int CHECK (quantity > 0)")
    Integer quantity;

    @Column(name = "unit_price", columnDefinition = "DECIMAL(11,2) CHECK (unit_price > 0)")
    Double unitPrice;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    Order order;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Product product;

    public void addProduct (Product product) {
        this.product = product;
    }

}
