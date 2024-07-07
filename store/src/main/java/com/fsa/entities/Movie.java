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
@Table(name = "movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie extends Product implements Serializable {

    @Column(name = "duration", columnDefinition = "DECIMAL(4,2) CHECK (duration > 0)")
    Double duration;

    @Column(columnDefinition = "TEXT")
    String reviews;

}
