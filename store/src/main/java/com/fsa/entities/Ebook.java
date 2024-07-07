package com.fsa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ebooks")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ebook extends Product implements Serializable {

    @Column(length = 100)
    String publisher;

    @Column(name = "number_of_pages", length = 10)
    Integer numberOfPages;

}
