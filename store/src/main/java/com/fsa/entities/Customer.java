package com.fsa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String name;

    @Column(length = 10)
    String phone;

    @Email
    @Column(nullable = false, length = 100)
    String email;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    Set<Order> orders;

    public void addOrder(Order order) {
        order.setCustomer(this);
        this.orders.add(order);
    }
}
