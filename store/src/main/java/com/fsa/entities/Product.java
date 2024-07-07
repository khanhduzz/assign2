package com.fsa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String title;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(name = "unit_price", columnDefinition = "DECIMAL(11,2) CHECK (unit_price > 0)")
    Double unitPrice;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_date")
    LocalDate releaseDate;

    @Column(length = 50)
    String language;

    @Column(length = 50)
    String genre;

    @OneToMany(mappedBy = "product")
    Set<OrderLine> orderLines;

}
