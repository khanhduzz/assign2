package com.fsa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ebooks")
public class Ebook {

    @EmbeddedId ProductId id;

    @Column(length = 100)
    String publisher;

    @Column(name = "number_of_pages", length = 10)
    Integer numberOfPages;

    @MapsId("id")
    @ManyToOne
    Product product;
}
